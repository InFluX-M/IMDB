import tensorflow as tf
from tensorflow import keras

import email_service
import rec_mails
from recsys_utils import *
import pandas as pd
import random
import mysql_service
from warnings import simplefilter
simplefilter(action="ignore", category=pd.errors.PerformanceWarning)

df_movies = pd.read_csv("movie.csv")
df_ratings = pd.read_csv("rating.csv")

user_ratings = mysql_service.user_ratings()

users = mysql_service.load_users_db()
num_users_db = len(users)


def create_map_title_id_to_id(df_movies_db):
    map_title_id_to_id = dict()
    for idx_movie in range(len(df_movies_db)):
        map_title_id_to_id[df_movies_db.iloc[idx_movie]["title_id"]] = idx_movie
    return map_title_id_to_id


def create_map_id_to_email(users_db):
    map_id_to_email = dict()
    for user in users_db:
        map_id_to_email[user["id"]] = user["email"]
    return map_id_to_email


def create_map_email_to_id(users_db):
    map_email_to_id = dict()
    for user in users_db:
        map_email_to_id[user["email"]] = user["id"]
    return map_email_to_id


id_email = create_map_id_to_email(users)

email_id = create_map_email_to_id(users)


def create_random_user_based_on_average_rating_movies(Y_parameter, R_parameter):
    for idx_user in range(120):
        ls = [0 for _ in range(267)]
        Y_parameter["User" + str(idx_user)] = ls
        R_parameter["User" + str(idx_user)] = ls

    for idx_movie in range(267):
        title_id_movie = df_movies.iloc[idx_movie]["title_id"]
        avg = df_ratings[df_ratings["movie_title_id"] == title_id_movie]["avg_rating"]
        avg = avg.iloc[0]
        j = random.randint(0, 5)
        while True:
            rate = random.randint(int(max(avg - random.randint(0, 3), 0)), int(min(avg + random.randint(0, 3), 10)))

            Y_parameter.at[idx_movie, "User" + str(j)] = rate
            R_parameter.at[idx_movie, "User" + str(j)] = 1

            if avg != 0 and j % (int(avg) * 3 + 10) == 0:
                u = random.randint(0, 5)
                Y_parameter.at[idx_movie, "User" + str(j + u)] = random.randint(1, 4)
                R_parameter.at[idx_movie, "User" + str(j + u)] = 1
            j += random.randint(3, 7)

            if j > 119:
                break
    return Y_parameter, R_parameter


def cost_func_v(X_parameter, W_parameter, b_parameter, Y_parameter, R_parameter, lambdaZ):
    j = (tf.linalg.matmul(X_parameter, tf.transpose(W_parameter)) + b_parameter - Y_parameter) * R_parameter
    J = 0.5 * tf.reduce_sum(j ** 2) + (lambdaZ / 2) * (
                tf.reduce_sum(X_parameter ** 2) + tf.reduce_sum(W_parameter ** 2))
    return J


def define_model_and_parameters(num_movies_parameter, num_users_parameter, num_features_parameter):
    tf.random.set_seed(1234)
    W_parameter = tf.Variable(tf.random.normal((num_users_parameter, num_features_parameter), dtype=tf.float64), name='W')
    X_parameter = tf.Variable(tf.random.normal((num_movies_parameter, num_features_parameter), dtype=tf.float64), name='X')
    b_parameter = tf.Variable(tf.random.normal((1, num_users_parameter), dtype=tf.float64), name='b')

    keras_optimizer = keras.optimizers.Adam(learning_rate=1e-1)

    return W_parameter, X_parameter, b_parameter, keras_optimizer


def add_user_ratings_to_matrix(Y_parameter, R_parameter, user_ratings_pd, title_id_to_id):
    for user in user_ratings_pd:
        for title_id in user_ratings_pd[user]:
            title_id = title_id[:9]
            Y_parameter.at[title_id_to_id[title_id], user] = user_ratings_pd[user][title_id]
            R_parameter.at[title_id_to_id[title_id], user] = int(1)

    Y_parameter.fillna(value=int(0), inplace=True)
    R_parameter.fillna(value=int(0), inplace=True)

    return Y_parameter, R_parameter


def train_model(X_parameter, W_parameter, b_parameter, Y_norm_model, R_model, lambda_, ITER):
    for ITER in range(ITER):
        with tf.GradientTape() as tape:
            cost_value = cost_func_v(X, W, b, Y_norm_model, R_model, lambda_)

        grads = tape.gradient(cost_value, [X, W, b])
        optimizer.apply_gradients(zip(grads, [X, W, b]))

        if ITER % 20 == 0:
            print(f"Training loss at iteration {ITER}: {cost_value:0.1f}")
    return X_parameter, W_parameter, b_parameter


def predict_rating(X_parameter, W_parameter, b_parameter, Y_mean_parameter):
    return (np.matmul(X_parameter.numpy(), np.transpose(W_parameter.numpy())) + b_parameter.numpy()) + Y_mean_parameter


def send_mail_top_rated_movies(num_users_model, predictions_model, id_email_model):
    for idx_user_mail in range(num_users_model):
        user_predictions = predictions_model[:, 120 + idx_user_mail]
        ix = tf.argsort(user_predictions, direction='DESCENDING')

        top_rated = list()
        for rate_user in ix.numpy():
            if R[rate_user][120 + idx_user_mail] == 0:
                top_rated.append(rate_user)
            if len(top_rated) == 5:
                break

        recommended_movie = dict()
        for movie in top_rated:
            recommended_movie[df_movies.loc[movie, "title_id"]] = [user_predictions[movie], df_movies.loc[movie, "title"]]

        print(recommended_movie)
        email_service.send_recommend_movies_to_user_mail(id_email_model[idx_user_mail], recommended_movie)


def edit_Y_R_Values(title_id_to_id):
    response_ratings = rec_mails.email_receiver()
    mysql_service.insert_user_ratings(response_ratings, email_id)

    for user in response_ratings:
        id_user = email_id[user]
        for title_id in response_ratings[user]:
            id_movie = title_id_to_id[title_id]
            Y[id_movie, id_user+120] = response_ratings[user][title_id]
            R[id_movie, id_user+120] = 1

    return Y, R


Y = pd.DataFrame()
R = pd.DataFrame()

title_id_to_idx = create_map_title_id_to_id(df_movies)

Y, R = create_random_user_based_on_average_rating_movies(Y, R)
Y, R = add_user_ratings_to_matrix(Y, R, user_ratings, title_id_to_idx)

# j = 0
# for i in Y.iloc[0:267, 120]:
#     if i != 0:
#         print(df_movies.iloc[j]["title_id"], i)
#     j += 1

num_movies, num_users = Y.shape
num_features = 1500

map_Y_columns_to_user_id = dict()
for idx in range(num_users):
    map_Y_columns_to_user_id[Y.columns[idx]] = idx

W, X, b, optimizer = define_model_and_parameters(num_movies, num_users, num_features)

Y = np.c_[Y]
R = np.c_[R]

Y_norm, Y_mean = normalizeRatings(Y, R)

X, W, b = train_model(X, W, b, Y_norm, R, 1, 760)

predictions = predict_rating(X, W, b, Y_mean)


# i = 0
# for rate in predictions[:, 120]:
#     print(rate, df_movies.iloc[i]["title_id"])
#     i = i+1


send_mail_top_rated_movies(num_users_db, predictions, id_email)

Y, R = edit_Y_R_Values(title_id_to_idx)

# not_zeroes = 0
# for i in range(num_movies):
#     if R[i, 120] != 0:
#         not_zeroes = not_zeroes + 1
# print(not_zeroes)

W, X, b, optimizer = define_model_and_parameters(num_movies, num_users, num_features)

Y = np.c_[Y]
R = np.c_[R]

Y_norm, Y_mean = normalizeRatings(Y, R)

X, W, b = train_model(X, W, b, Y_norm, R, 1, 760)

predictions = predict_rating(X, W, b, Y_mean)

send_mail_top_rated_movies(num_users_db, predictions, id_email)

# i = 0
# for rate in predictions[:, 120]:
#     if df_movies.iloc[i]["title_id"] == "tt0468569":
#         print("-------------------------------")
#         print(rate, df_movies.iloc[i]["title_id"])
#     print(rate, df_movies.iloc[i]["title_id"], df_movies.iloc[i]["title"])
#     i = i+1

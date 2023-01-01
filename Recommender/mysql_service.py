import pymysql.cursors
import pymysql



def load_user_ratings_db():
    connection = pymysql.connect(host='localhost',
                                 user='root',
                                 password='',
                                 db='imdb',
                                 charset='utf8mb4',
                                 cursorclass=pymysql.cursors.DictCursor)

    try:

        with connection.cursor() as cursor:
            sql = "SELECT `rating`, `title_id`, `user_id` FROM `user_rating`"
            cursor.execute(sql)
            result = cursor.fetchall()
            result = list(result)
            for i in range(len(result)):
                result[i]["title_id"] = result[i]["title_id"][:9]

    finally:
        connection.close()

    return result


def load_users_db():
    connection = pymysql.connect(host='localhost',
                                 user='root',
                                 password='',
                                 db='imdb',
                                 charset='utf8mb4',
                                 cursorclass=pymysql.cursors.DictCursor)

    try:

        with connection.cursor() as cursor:
            sql = "SELECT `id`, `email`, `username` FROM `user`"
            cursor.execute(sql)
            result = cursor.fetchall()
            result = list(result)

    finally:
        connection.close()

    return result


def user_ratings():
    connection = pymysql.connect(host='localhost',
                                 user='root',
                                 password='',
                                 db='imdb',
                                 charset='utf8mb4',
                                 cursorclass=pymysql.cursors.DictCursor)

    ratings = dict()
    rates = load_user_ratings_db()

    for rate in rates:
        if rate["user_id"] not in ratings:
            ratings[rate["user_id"]] = dict()
        ratings[rate["user_id"]][rate["title_id"]] = rate["rating"]

    return ratings


def insert_user_ratings(response_ratings, email_id):
    connection = pymysql.connect(host='localhost',
                                 user='root',
                                 password='',
                                 db='imdb',
                                 charset='utf8mb4',
                                 cursorclass=pymysql.cursors.DictCursor)

    try:
        with connection.cursor() as cursor:
            for user_mail in response_ratings:
                for title_id in response_ratings[user_mail]:
                    sql = "INSERT INTO `user_rating` (`rating`, `title_id`, `user_id`) VALUES (%s, %s, %s)"
                    cursor.execute(sql, (int(response_ratings[user_mail][title_id]), title_id, email_id[user_mail]))
                    connection.commit()
    finally:
        connection.close()
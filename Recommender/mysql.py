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
            # Read a single record
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
            # Read a single record
            sql = "SELECT `id`, `email`, `username` FROM `user`"
            cursor.execute(sql)
            result = cursor.fetchall()
            result = list(result)
            print(result)

    finally:
        connection.close()

    return result


def user_ratings():
    ratings = dict()
    rates = load_user_ratings_db()

    for rate in rates:
        if rate["user_id"] not in ratings:
            ratings[rate["user_id"]] = dict()
        ratings[rate["user_id"]][rate["title_id"]] = rate["rating"]

    return ratings

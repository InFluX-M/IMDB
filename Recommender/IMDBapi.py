# import requests
from requests.adapters import HTTPAdapter
import tmdbsimple as tmdb
tmdb.API_KEY = '54c8c375cdffd93a05f3bb48d139a741'
tmdb.REQUESTS_TIMEOUT = (2, 5)


def get_movie_poster(title_id):
    data = tmdb.Movies(title_id).info()
    print(data)
    return "http://image.tmdb.org/t/p/original"+data["backdrop_path"]

# title_id = "tt0111161"
# data = tmdb.Movies(title_id).info()
# print(data)
    # return "http://image.tmdb.org/t/p/original"+data["backdrop_path"]
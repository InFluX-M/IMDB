import requests


def get_movie_poster(title_id):
    URL = f"https://api.themoviedb.org/3/movie/{title_id}"
    location = "54c8c375cdffd93a05f3bb48d139a741"
    PARAMS = {'api_key': location}
    r = requests.get(url=URL, params=PARAMS)
    data = r.json()
    return "http://image.tmdb.org/t/p/original"+data["backdrop_path"]

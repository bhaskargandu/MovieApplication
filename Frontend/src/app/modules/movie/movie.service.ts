import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Movie } from './movie';
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  apiKey: string;
  tmdbEndpoint: string ;
  imagePrefix: string;
  springEndpoint: string;
  search: string;

  constructor(private http: HttpClient) {
    this.apiKey = 'api_key=f901cbb9e3e99891f2881ef3a15ae5ca';
    this.tmdbEndpoint = 'http://api.themoviedb.org/3/movie';
    this.search = 'http://api.themoviedb.org/3/search/movie';
    this.imagePrefix = 'https://image.tmdb.org/t/p/w500';

    this.springEndpoint = 'http://localhost:8082/api/v1/movieservice';
  }

  getMovie(type: string, page: number = 1): Observable<Array<Movie>> {
    const endpoint = `${this.tmdbEndpoint}/${type}?${this.apiKey}&page=${page}`;
    return this.http.get(endpoint).pipe(
      retry(3),
      map(this.pickMovieResult),
      map(this.transformPosterPath.bind(this))
    );
  }


  transformPosterPath(movies): Array<Movie> {
    movies.map(movie => {
      movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
      return movie;
    });
    return movies;
  }

  pickMovieResult(response): Array<Movie> {
    return response['results'];
  }

  addMovieToWatchlist(movie): Observable<Movie> {
    const url = `${this.springEndpoint}/movie`;
    return this.http.post<Movie>(url, movie);
  }

  deleteMovieFromWatchlist(movie) {
    const url = `${this.springEndpoint}/movie/${movie.id}`;
    return this.http.delete(url, {responseType: 'text'});
  }

  updateComments(movie) {
    const url = `${this.springEndpoint}/movie/${movie.id}`;
    return this.http.put(url, movie);
  }

  getWatchlistedMovies(): Observable<Array<Movie>> {
    const url = `${this.springEndpoint}/movies`;
    return this.http.get<Array<Movie>>(url);
  }
  searchMovies(searchKey: string): Observable<Array<Movie>> {
    if (searchKey.length > 0) {
      const url = `${this.search}?${this.apiKey}&page=1&include_adult=false&query=${searchKey}`;
      console.log(url);

      return this.http.get(url).pipe(
        retry(3),
        map(this.pickMovieResult),
        map(this.transformPosterPath.bind(this))
      );
    }
  }
}


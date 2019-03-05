import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { ActivatedRoute } from '@angular/router';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  movies: Array<Movie>;
  @Input()
  useWatchlistApi: boolean;

  constructor(private movieService: MovieService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
  }

  addMovieToWatchlist(movie) {
    const message = `${movie.title} add to watchlist`
    this.movieService.addMovieToWatchlist(movie).subscribe(() => {
      this.snackBar.open(message, '', {
        duration: 1000
      });
    });
  }

  deleteMovieFromWatchlist(movie: Movie) {
    const message = `${movie.title} delete from your watchlist`;
    for (let i = 0; i < this.movies.length; i++) {
      if (this.movies[i].title === movie.title) {
        this.movies.splice(i, 1);
      }
    }

    this.movieService.deleteMovieFromWatchlist(movie).subscribe(() => {
      this.snackBar.open(message, '', {
        duration: 1000
      });
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movies: Array<Movie>;
  useWatchlistApi1 = true;

  constructor(private movieService: MovieService, private snackBar: MatSnackBar) {
    this.movies = [];
  }

  ngOnInit() {
    const message = 'Watchlist is Empty';
    this.movieService.getWatchlistedMovies().subscribe((movies) => {
      if (movies.length === 0) {
        this.snackBar.open(message, '', {
          duration: 1000
        });
      }
      this.movies.push(...movies);
    });
  }

}

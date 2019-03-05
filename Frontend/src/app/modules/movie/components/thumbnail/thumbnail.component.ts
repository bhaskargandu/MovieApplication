import {Component, OnInit, Input, EventEmitter, Output} from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import {MovieDialogComponent} from '../movie-dialog/movie-dialog.component';


@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input()
  movie: Movie;
  @Input()
  useWatchlistApi: boolean;
  @Output()
  deleteMovie = new EventEmitter();
  @Output()
  addMovie = new EventEmitter();
  @Output()
  updateMovie = new EventEmitter();

  constructor(private dialog: MatDialog) {
  }

  ngOnInit() {
  }

  addToWatchlist() {
    this.addMovie.emit(this.movie);
  }

  deleteFromWatchlist() {
    this.deleteMovie.emit(this.movie);
  }

  updateFromWatchlist(actionType) {
    console.log('Movie is getting updated.');
    const dialogRef = this.dialog.open(MovieDialogComponent, {
      width: '400px',
      data: { obj: this.movie, actionType: actionType }
    });

    console.log('open dialog');
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed.');
    });
  }
}

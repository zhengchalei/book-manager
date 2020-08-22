import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBookType } from 'app/shared/model/book-type.model';

@Component({
  selector: 'jhi-book-type-detail',
  templateUrl: './book-type-detail.component.html',
})
export class BookTypeDetailComponent implements OnInit {
  bookType: IBookType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bookType }) => (this.bookType = bookType));
  }

  previousState(): void {
    window.history.back();
  }
}

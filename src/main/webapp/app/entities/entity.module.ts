import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'book-type',
        loadChildren: () => import('./book-type/book-type.module').then(m => m.BookBookTypeModule),
      },
      {
        path: 'book',
        loadChildren: () => import('./book/book.module').then(m => m.BookBookModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BookEntityModule {}

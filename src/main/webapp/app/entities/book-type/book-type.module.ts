import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookSharedModule } from 'app/shared/shared.module';
import { BookTypeComponent } from './book-type.component';
import { BookTypeDetailComponent } from './book-type-detail.component';
import { BookTypeUpdateComponent } from './book-type-update.component';
import { BookTypeDeleteDialogComponent } from './book-type-delete-dialog.component';
import { bookTypeRoute } from './book-type.route';

@NgModule({
  imports: [BookSharedModule, RouterModule.forChild(bookTypeRoute)],
  declarations: [BookTypeComponent, BookTypeDetailComponent, BookTypeUpdateComponent, BookTypeDeleteDialogComponent],
  entryComponents: [BookTypeDeleteDialogComponent],
})
export class BookBookTypeModule {}

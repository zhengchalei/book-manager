import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBookType } from 'app/shared/model/book-type.model';
import { BookTypeService } from './book-type.service';

@Component({
  templateUrl: './book-type-delete-dialog.component.html',
})
export class BookTypeDeleteDialogComponent {
  bookType?: IBookType;

  constructor(protected bookTypeService: BookTypeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bookTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bookTypeListModification');
      this.activeModal.close();
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBookType, BookType } from 'app/shared/model/book-type.model';
import { BookTypeService } from './book-type.service';

@Component({
  selector: 'jhi-book-type-update',
  templateUrl: './book-type-update.component.html',
})
export class BookTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    remark: [],
  });

  constructor(protected bookTypeService: BookTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bookType }) => {
      this.updateForm(bookType);
    });
  }

  updateForm(bookType: IBookType): void {
    this.editForm.patchValue({
      id: bookType.id,
      name: bookType.name,
      remark: bookType.remark,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bookType = this.createFromForm();
    if (bookType.id !== undefined) {
      this.subscribeToSaveResponse(this.bookTypeService.update(bookType));
    } else {
      this.subscribeToSaveResponse(this.bookTypeService.create(bookType));
    }
  }

  private createFromForm(): IBookType {
    return {
      ...new BookType(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      remark: this.editForm.get(['remark'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBookType>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}

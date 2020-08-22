import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IBook, Book } from 'app/shared/model/book.model';
import { BookService } from './book.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IBookType } from 'app/shared/model/book-type.model';
import { BookTypeService } from 'app/entities/book-type/book-type.service';

@Component({
  selector: 'jhi-book-update',
  templateUrl: './book-update.component.html',
})
export class BookUpdateComponent implements OnInit {
  isSaving = false;
  booktypes: IBookType[] = [];
  createTimeDp: any;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    pic: [],
    picContentType: [],
    createTime: [],
    bookStatus: [null, [Validators.required]],
    remark: [],
    bookTypeId: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected bookService: BookService,
    protected bookTypeService: BookTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ book }) => {
      this.updateForm(book);

      this.bookTypeService.query().subscribe((res: HttpResponse<IBookType[]>) => (this.booktypes = res.body || []));
    });
  }

  updateForm(book: IBook): void {
    this.editForm.patchValue({
      id: book.id,
      name: book.name,
      pic: book.pic,
      picContentType: book.picContentType,
      createTime: book.createTime,
      bookStatus: book.bookStatus,
      remark: book.remark,
      bookTypeId: book.bookTypeId,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('bookApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const book = this.createFromForm();
    if (book.id !== undefined) {
      this.subscribeToSaveResponse(this.bookService.update(book));
    } else {
      this.subscribeToSaveResponse(this.bookService.create(book));
    }
  }

  private createFromForm(): IBook {
    return {
      ...new Book(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      picContentType: this.editForm.get(['picContentType'])!.value,
      pic: this.editForm.get(['pic'])!.value,
      createTime: this.editForm.get(['createTime'])!.value,
      bookStatus: this.editForm.get(['bookStatus'])!.value,
      remark: this.editForm.get(['remark'])!.value,
      bookTypeId: this.editForm.get(['bookTypeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBook>>): void {
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

  trackById(index: number, item: IBookType): any {
    return item.id;
  }
}

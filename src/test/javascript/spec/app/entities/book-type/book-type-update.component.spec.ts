import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BookTestModule } from '../../../test.module';
import { BookTypeUpdateComponent } from 'app/entities/book-type/book-type-update.component';
import { BookTypeService } from 'app/entities/book-type/book-type.service';
import { BookType } from 'app/shared/model/book-type.model';

describe('Component Tests', () => {
  describe('BookType Management Update Component', () => {
    let comp: BookTypeUpdateComponent;
    let fixture: ComponentFixture<BookTypeUpdateComponent>;
    let service: BookTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookTestModule],
        declarations: [BookTypeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BookTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BookTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BookTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BookType(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new BookType();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookTestModule } from '../../../test.module';
import { BookTypeDetailComponent } from 'app/entities/book-type/book-type-detail.component';
import { BookType } from 'app/shared/model/book-type.model';

describe('Component Tests', () => {
  describe('BookType Management Detail Component', () => {
    let comp: BookTypeDetailComponent;
    let fixture: ComponentFixture<BookTypeDetailComponent>;
    const route = ({ data: of({ bookType: new BookType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookTestModule],
        declarations: [BookTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BookTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BookTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bookType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bookType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

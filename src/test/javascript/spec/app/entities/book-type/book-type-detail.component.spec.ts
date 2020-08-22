import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { BookTestModule } from '../../../test.module';
import { BookTypeDetailComponent } from 'app/entities/book-type/book-type-detail.component';
import { BookType } from 'app/shared/model/book-type.model';

describe('Component Tests', () => {
  describe('BookType Management Detail Component', () => {
    let comp: BookTypeDetailComponent;
    let fixture: ComponentFixture<BookTypeDetailComponent>;
    let dataUtils: JhiDataUtils;
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
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load bookType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bookType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});

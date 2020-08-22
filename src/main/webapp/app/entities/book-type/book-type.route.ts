import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBookType, BookType } from 'app/shared/model/book-type.model';
import { BookTypeService } from './book-type.service';
import { BookTypeComponent } from './book-type.component';
import { BookTypeDetailComponent } from './book-type-detail.component';
import { BookTypeUpdateComponent } from './book-type-update.component';

@Injectable({ providedIn: 'root' })
export class BookTypeResolve implements Resolve<IBookType> {
  constructor(private service: BookTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBookType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bookType: HttpResponse<BookType>) => {
          if (bookType.body) {
            return of(bookType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BookType());
  }
}

export const bookTypeRoute: Routes = [
  {
    path: '',
    component: BookTypeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'bookApp.bookType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BookTypeDetailComponent,
    resolve: {
      bookType: BookTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bookApp.bookType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BookTypeUpdateComponent,
    resolve: {
      bookType: BookTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bookApp.bookType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BookTypeUpdateComponent,
    resolve: {
      bookType: BookTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bookApp.bookType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

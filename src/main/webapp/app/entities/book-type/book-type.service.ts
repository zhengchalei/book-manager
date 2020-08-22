import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBookType } from 'app/shared/model/book-type.model';

type EntityResponseType = HttpResponse<IBookType>;
type EntityArrayResponseType = HttpResponse<IBookType[]>;

@Injectable({ providedIn: 'root' })
export class BookTypeService {
  public resourceUrl = SERVER_API_URL + 'api/book-types';

  constructor(protected http: HttpClient) {}

  create(bookType: IBookType): Observable<EntityResponseType> {
    return this.http.post<IBookType>(this.resourceUrl, bookType, { observe: 'response' });
  }

  update(bookType: IBookType): Observable<EntityResponseType> {
    return this.http.put<IBookType>(this.resourceUrl, bookType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBookType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBookType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

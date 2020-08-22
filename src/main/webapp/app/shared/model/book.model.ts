import { Moment } from 'moment';
import { BookStatus } from 'app/shared/model/enumerations/book-status.model';

export interface IBook {
  id?: number;
  name?: string;
  picContentType?: string;
  pic?: any;
  createTime?: Moment;
  bookStatus?: BookStatus;
  remark?: any;
  bookTypeName?: string;
  bookTypeId?: number;
}

export class Book implements IBook {
  constructor(
    public id?: number,
    public name?: string,
    public picContentType?: string,
    public pic?: any,
    public createTime?: Moment,
    public bookStatus?: BookStatus,
    public remark?: any,
    public bookTypeName?: string,
    public bookTypeId?: number
  ) {}
}

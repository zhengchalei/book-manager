export interface IBookType {
  id?: number;
  name?: string;
  remark?: any;
}

export class BookType implements IBookType {
  constructor(public id?: number, public name?: string, public remark?: any) {}
}

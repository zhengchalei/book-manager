package cn.runjava.book.service.mapper;

import cn.runjava.book.domain.*;
import cn.runjava.book.service.dto.BookTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BookType} and its DTO {@link BookTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookTypeMapper extends EntityMapper<BookTypeDTO, BookType> {
    default BookType fromId(Long id) {
        if (id == null) {
            return null;
        }
        BookType bookType = new BookType();
        bookType.setId(id);
        return bookType;
    }
}

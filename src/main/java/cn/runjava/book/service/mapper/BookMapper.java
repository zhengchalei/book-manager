package cn.runjava.book.service.mapper;

import cn.runjava.book.domain.*;
import cn.runjava.book.service.dto.BookDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(componentModel = "spring", uses = { BookTypeMapper.class })
public interface BookMapper extends EntityMapper<BookDTO, Book> {
    @Mapping(source = "bookType.id", target = "bookTypeId")
    @Mapping(source = "bookType.name", target = "bookTypeName")
    BookDTO toDto(Book book);

    @Mapping(source = "bookTypeId", target = "bookType")
    Book toEntity(BookDTO bookDTO);

    default Book fromId(Long id) {
        if (id == null) {
            return null;
        }
        Book book = new Book();
        book.setId(id);
        return book;
    }
}

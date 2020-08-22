package cn.runjava.book.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTypeMapperTest {
    private BookTypeMapper bookTypeMapper;

    @BeforeEach
    public void setUp() {
        bookTypeMapper = new BookTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(bookTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bookTypeMapper.fromId(null)).isNull();
    }
}

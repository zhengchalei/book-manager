package cn.runjava.book.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookMapperTest {
    private BookMapper bookMapper;

    @BeforeEach
    public void setUp() {
        bookMapper = new BookMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(bookMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bookMapper.fromId(null)).isNull();
    }
}

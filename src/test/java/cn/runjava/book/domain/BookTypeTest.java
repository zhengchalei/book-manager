package cn.runjava.book.domain;

import static org.assertj.core.api.Assertions.assertThat;

import cn.runjava.book.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BookTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookType.class);
        BookType bookType1 = new BookType();
        bookType1.setId(1L);
        BookType bookType2 = new BookType();
        bookType2.setId(bookType1.getId());
        assertThat(bookType1).isEqualTo(bookType2);
        bookType2.setId(2L);
        assertThat(bookType1).isNotEqualTo(bookType2);
        bookType1.setId(null);
        assertThat(bookType1).isNotEqualTo(bookType2);
    }
}

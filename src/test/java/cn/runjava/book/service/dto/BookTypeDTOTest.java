package cn.runjava.book.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import cn.runjava.book.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BookTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookTypeDTO.class);
        BookTypeDTO bookTypeDTO1 = new BookTypeDTO();
        bookTypeDTO1.setId(1L);
        BookTypeDTO bookTypeDTO2 = new BookTypeDTO();
        assertThat(bookTypeDTO1).isNotEqualTo(bookTypeDTO2);
        bookTypeDTO2.setId(bookTypeDTO1.getId());
        assertThat(bookTypeDTO1).isEqualTo(bookTypeDTO2);
        bookTypeDTO2.setId(2L);
        assertThat(bookTypeDTO1).isNotEqualTo(bookTypeDTO2);
        bookTypeDTO1.setId(null);
        assertThat(bookTypeDTO1).isNotEqualTo(bookTypeDTO2);
    }
}

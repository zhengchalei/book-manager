package cn.runjava.book.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import cn.runjava.book.BookApp;
import cn.runjava.book.domain.BookType;
import cn.runjava.book.repository.BookTypeRepository;
import cn.runjava.book.service.BookTypeService;
import cn.runjava.book.service.dto.BookTypeDTO;
import cn.runjava.book.service.mapper.BookTypeMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BookTypeResource} REST controller.
 */
@SpringBootTest(classes = BookApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BookTypeResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Autowired
    private BookTypeService bookTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBookTypeMockMvc;

    private BookType bookType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BookType createEntity(EntityManager em) {
        BookType bookType = new BookType().name(DEFAULT_NAME).remark(DEFAULT_REMARK);
        return bookType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BookType createUpdatedEntity(EntityManager em) {
        BookType bookType = new BookType().name(UPDATED_NAME).remark(UPDATED_REMARK);
        return bookType;
    }

    @BeforeEach
    public void initTest() {
        bookType = createEntity(em);
    }

    @Test
    @Transactional
    public void createBookType() throws Exception {
        int databaseSizeBeforeCreate = bookTypeRepository.findAll().size();
        // Create the BookType
        BookTypeDTO bookTypeDTO = bookTypeMapper.toDto(bookType);
        restBookTypeMockMvc
            .perform(
                post("/api/book-types").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bookTypeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BookType in the database
        List<BookType> bookTypeList = bookTypeRepository.findAll();
        assertThat(bookTypeList).hasSize(databaseSizeBeforeCreate + 1);
        BookType testBookType = bookTypeList.get(bookTypeList.size() - 1);
        assertThat(testBookType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBookType.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createBookTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookTypeRepository.findAll().size();

        // Create the BookType with an existing ID
        bookType.setId(1L);
        BookTypeDTO bookTypeDTO = bookTypeMapper.toDto(bookType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookTypeMockMvc
            .perform(
                post("/api/book-types").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bookTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BookType in the database
        List<BookType> bookTypeList = bookTypeRepository.findAll();
        assertThat(bookTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookTypeRepository.findAll().size();
        // set the field null
        bookType.setName(null);

        // Create the BookType, which fails.
        BookTypeDTO bookTypeDTO = bookTypeMapper.toDto(bookType);

        restBookTypeMockMvc
            .perform(
                post("/api/book-types").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bookTypeDTO))
            )
            .andExpect(status().isBadRequest());

        List<BookType> bookTypeList = bookTypeRepository.findAll();
        assertThat(bookTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBookTypes() throws Exception {
        // Initialize the database
        bookTypeRepository.saveAndFlush(bookType);

        // Get all the bookTypeList
        restBookTypeMockMvc
            .perform(get("/api/book-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bookType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }

    @Test
    @Transactional
    public void getBookType() throws Exception {
        // Initialize the database
        bookTypeRepository.saveAndFlush(bookType);

        // Get the bookType
        restBookTypeMockMvc
            .perform(get("/api/book-types/{id}", bookType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bookType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }

    @Test
    @Transactional
    public void getNonExistingBookType() throws Exception {
        // Get the bookType
        restBookTypeMockMvc.perform(get("/api/book-types/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBookType() throws Exception {
        // Initialize the database
        bookTypeRepository.saveAndFlush(bookType);

        int databaseSizeBeforeUpdate = bookTypeRepository.findAll().size();

        // Update the bookType
        BookType updatedBookType = bookTypeRepository.findById(bookType.getId()).get();
        // Disconnect from session so that the updates on updatedBookType are not directly saved in db
        em.detach(updatedBookType);
        updatedBookType.name(UPDATED_NAME).remark(UPDATED_REMARK);
        BookTypeDTO bookTypeDTO = bookTypeMapper.toDto(updatedBookType);

        restBookTypeMockMvc
            .perform(put("/api/book-types").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bookTypeDTO)))
            .andExpect(status().isOk());

        // Validate the BookType in the database
        List<BookType> bookTypeList = bookTypeRepository.findAll();
        assertThat(bookTypeList).hasSize(databaseSizeBeforeUpdate);
        BookType testBookType = bookTypeList.get(bookTypeList.size() - 1);
        assertThat(testBookType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBookType.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingBookType() throws Exception {
        int databaseSizeBeforeUpdate = bookTypeRepository.findAll().size();

        // Create the BookType
        BookTypeDTO bookTypeDTO = bookTypeMapper.toDto(bookType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookTypeMockMvc
            .perform(put("/api/book-types").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bookTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BookType in the database
        List<BookType> bookTypeList = bookTypeRepository.findAll();
        assertThat(bookTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBookType() throws Exception {
        // Initialize the database
        bookTypeRepository.saveAndFlush(bookType);

        int databaseSizeBeforeDelete = bookTypeRepository.findAll().size();

        // Delete the bookType
        restBookTypeMockMvc
            .perform(delete("/api/book-types/{id}", bookType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BookType> bookTypeList = bookTypeRepository.findAll();
        assertThat(bookTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

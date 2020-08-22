package cn.runjava.book.web.rest;

import cn.runjava.book.service.BookTypeService;
import cn.runjava.book.service.dto.BookTypeDTO;
import cn.runjava.book.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link cn.runjava.book.domain.BookType}.
 */
@RestController
@RequestMapping("/api")
public class BookTypeResource {
    private final Logger log = LoggerFactory.getLogger(BookTypeResource.class);

    private static final String ENTITY_NAME = "bookType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookTypeService bookTypeService;

    public BookTypeResource(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }

    /**
     * {@code POST  /book-types} : Create a new bookType.
     *
     * @param bookTypeDTO the bookTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bookTypeDTO, or with status {@code 400 (Bad Request)} if the bookType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/book-types")
    public ResponseEntity<BookTypeDTO> createBookType(@Valid @RequestBody BookTypeDTO bookTypeDTO) throws URISyntaxException {
        log.debug("REST request to save BookType : {}", bookTypeDTO);
        if (bookTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new bookType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookTypeDTO result = bookTypeService.save(bookTypeDTO);
        return ResponseEntity
            .created(new URI("/api/book-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /book-types} : Updates an existing bookType.
     *
     * @param bookTypeDTO the bookTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookTypeDTO,
     * or with status {@code 400 (Bad Request)} if the bookTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bookTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/book-types")
    public ResponseEntity<BookTypeDTO> updateBookType(@Valid @RequestBody BookTypeDTO bookTypeDTO) throws URISyntaxException {
        log.debug("REST request to update BookType : {}", bookTypeDTO);
        if (bookTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BookTypeDTO result = bookTypeService.save(bookTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bookTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /book-types} : get all the bookTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bookTypes in body.
     */
    @GetMapping("/book-types")
    public ResponseEntity<List<BookTypeDTO>> getAllBookTypes(Pageable pageable) {
        log.debug("REST request to get a page of BookTypes");
        Page<BookTypeDTO> page = bookTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /book-types/:id} : get the "id" bookType.
     *
     * @param id the id of the bookTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bookTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/book-types/{id}")
    public ResponseEntity<BookTypeDTO> getBookType(@PathVariable Long id) {
        log.debug("REST request to get BookType : {}", id);
        Optional<BookTypeDTO> bookTypeDTO = bookTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookTypeDTO);
    }

    /**
     * {@code DELETE  /book-types/:id} : delete the "id" bookType.
     *
     * @param id the id of the bookTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/book-types/{id}")
    public ResponseEntity<Void> deleteBookType(@PathVariable Long id) {
        log.debug("REST request to delete BookType : {}", id);
        bookTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

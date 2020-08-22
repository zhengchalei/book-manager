package cn.runjava.book.service.impl;

import cn.runjava.book.domain.BookType;
import cn.runjava.book.repository.BookTypeRepository;
import cn.runjava.book.service.BookTypeService;
import cn.runjava.book.service.dto.BookTypeDTO;
import cn.runjava.book.service.mapper.BookTypeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BookType}.
 */
@Service
@Transactional
public class BookTypeServiceImpl implements BookTypeService {
    private final Logger log = LoggerFactory.getLogger(BookTypeServiceImpl.class);

    private final BookTypeRepository bookTypeRepository;

    private final BookTypeMapper bookTypeMapper;

    public BookTypeServiceImpl(BookTypeRepository bookTypeRepository, BookTypeMapper bookTypeMapper) {
        this.bookTypeRepository = bookTypeRepository;
        this.bookTypeMapper = bookTypeMapper;
    }

    @Override
    public BookTypeDTO save(BookTypeDTO bookTypeDTO) {
        log.debug("Request to save BookType : {}", bookTypeDTO);
        BookType bookType = bookTypeMapper.toEntity(bookTypeDTO);
        bookType = bookTypeRepository.save(bookType);
        return bookTypeMapper.toDto(bookType);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BookTypes");
        return bookTypeRepository.findAll(pageable).map(bookTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookTypeDTO> findOne(Long id) {
        log.debug("Request to get BookType : {}", id);
        return bookTypeRepository.findById(id).map(bookTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookType : {}", id);
        bookTypeRepository.deleteById(id);
    }
}

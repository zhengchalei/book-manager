package cn.runjava.book.repository;

import cn.runjava.book.domain.BookType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BookType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Long> {}

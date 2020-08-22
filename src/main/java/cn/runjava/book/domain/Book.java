package cn.runjava.book.domain;

import cn.runjava.book.domain.enumeration.BookStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 书籍 @author xiaoshitou
 */
@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 书籍名称
     */
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 书籍描述
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 书籍描述
     */
    @Lob
    @Column(name = "pic")
    private byte[] pic;

    @Column(name = "pic_content_type")
    private String picContentType;

    /**
     * 上架时间
     */
    @Column(name = "create_time")
    private LocalDate createTime;

    /**
     * 书籍状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "book_status", nullable = false)
    private BookStatus bookStatus;

    @ManyToOne
    @JsonIgnoreProperties(value = "books", allowSetters = true)
    private BookType bookType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Book name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public Book remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte[] getPic() {
        return pic;
    }

    public Book pic(byte[] pic) {
        this.pic = pic;
        return this;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getPicContentType() {
        return picContentType;
    }

    public Book picContentType(String picContentType) {
        this.picContentType = picContentType;
        return this;
    }

    public void setPicContentType(String picContentType) {
        this.picContentType = picContentType;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public Book createTime(LocalDate createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public Book bookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
        return this;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public BookType getBookType() {
        return bookType;
    }

    public Book bookType(BookType bookType) {
        this.bookType = bookType;
        return this;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        return id != null && id.equals(((Book) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Book{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", remark='" + getRemark() + "'" +
            ", pic='" + getPic() + "'" +
            ", picContentType='" + getPicContentType() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", bookStatus='" + getBookStatus() + "'" +
            "}";
    }
}

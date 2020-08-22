package cn.runjava.book.service.dto;

import cn.runjava.book.domain.enumeration.BookStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link cn.runjava.book.domain.Book} entity.
 */
@ApiModel(description = "书籍 @author xiaoshitou")
public class BookDTO implements Serializable {
    private Long id;

    /**
     * 书籍名称
     */
    @NotNull
    @ApiModelProperty(value = "书籍名称", required = true)
    private String name;

    /**
     * 书籍图片
     */
    @ApiModelProperty(value = "书籍图片")
    @Lob
    private byte[] pic;

    private String picContentType;

    /**
     * 上架时间
     */
    @ApiModelProperty(value = "上架时间")
    private LocalDate createTime;

    /**
     * 书籍状态
     */
    @NotNull
    @ApiModelProperty(value = "书籍状态", required = true)
    private BookStatus bookStatus;

    /**
     * 书籍描述
     */
    @ApiModelProperty(value = "书籍描述")
    @Lob
    private String remark;

    private Long bookTypeId;

    private String bookTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getPicContentType() {
        return picContentType;
    }

    public void setPicContentType(String picContentType) {
        this.picContentType = picContentType;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Long bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookDTO)) {
            return false;
        }

        return id != null && id.equals(((BookDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", pic='" + getPic() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", bookStatus='" + getBookStatus() + "'" +
            ", remark='" + getRemark() + "'" +
            ", bookTypeId=" + getBookTypeId() +
            ", bookTypeName='" + getBookTypeName() + "'" +
            "}";
    }
}

package cn.runjava.book.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link cn.runjava.book.domain.BookType} entity.
 */
@ApiModel(description = "书籍类型 @author xiaoshitou")
public class BookTypeDTO implements Serializable {
    private Long id;

    /**
     * 书籍类型名称
     */
    @NotNull
    @ApiModelProperty(value = "书籍类型名称", required = true)
    private String name;

    /**
     * 书籍类型描述
     */
    @ApiModelProperty(value = "书籍类型描述")
    @Lob
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((BookTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}

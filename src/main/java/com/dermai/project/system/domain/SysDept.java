package com.dermai.project.system.domain;

import com.dermai.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * sys_dept
 *
 * @author Shaobo
 */
@Setter
public class SysDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Department ID */
    @Getter
    private Long deptId;

    /** Parent ID */
    @Getter
    private Long parentId;

    /** Ancestors */
    @Getter
    private String ancestors;

    /** Department Name */
    private String deptName;

    /** Order Number */
    private Integer orderNum;

    /** Leader */
    @Getter
    private String leader;

    /** Phone Number */
    private String phone;

    /** Email */
    private String email;

    /** Department status: 0 Normal, 1 Deactivated */
    @Getter
    private String status;

    /** Delete flag (0 presence, 2 deletion) */
    @Getter
    private String delFlag;

    /** Parent Dept Name */
    @Getter
    private String parentName;

    /** Children Dept */
    @Getter
    private List<SysDept> children = new ArrayList<>();

    @NotBlank(message = "Department name cannot be empty")
    @Size(min = 0, max = 30, message = "Department names cannot exceed 30 characters")
    public String getDeptName()
    {
        return deptName;
    }

    @NotNull(message = "The display order cannot be empty")
    public Integer getOrderNum()
    {
        return orderNum;
    }

    @Size(min = 0, max = 11, message = "The length of the contact phone number should not exceed 11 characters")
    public String getPhone()
    {
        return phone;
    }

    @Email(message = "Incorrect mailbox format")
    @Size(min = 0, max = 50, message = "Incorrect mailbox format")
    public String getEmail()
    {
        return email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("parentId", getParentId())
                .append("ancestors", getAncestors())
                .append("deptName", getDeptName())
                .append("orderNum", getOrderNum())
                .append("leader", getLeader())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
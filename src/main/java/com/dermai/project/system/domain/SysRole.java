package com.dermai.project.system.domain;

import com.dermai.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * sys_role
 *
 * @author Shaobo
 */
@Setter
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Role ID */
    @Getter
    private Long roleId;

    /** Role Name */
    private String roleName;

    /** Role Auth */
    private String roleKey;

    /** Role Sort */
    private Integer roleSort;

    /** Data scope (1: all data permissions; 2: customized data permissions; 3: data permissions for this department;
     * 4: data permissions for this department and below; 5: data permissions for me only) */
    @Getter
    private String dataScope;

    /** Whether the menu tree selection items are displayed in association (0: parent and child are not displayed
     * in association with each other 1: parent and child are displayed in association with each other) */
    @Getter
    private boolean menuCheckStrictly;

    /** Whether the selection items of the department tree are related to each other
     * (0: parent and child are not related to each other 1: parent and child are related to each other) */
    @Getter
    private boolean deptCheckStrictly;

    /** Role status (0 normal 1 deactivated) */
    @Getter
    private String status;

    /** Delete flag (0 presence 2 deletion) */
    @Getter
    private String delFlag;

    /** Whether this role identifier exists for the user, Default No */
    @Getter
    private boolean flag = false;

    /** Menu Ids */
    @Getter
    private Long[] menuIds;

    /** Depts List */
    @Getter
    private Long[] deptIds;

    /** Permissions */
    @Getter
    private Set<String> permissions;

    public SysRole()
    {

    }

    public SysRole(Long roleId)
    {
        this.roleId = roleId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.roleId);
    }

    public static boolean isAdmin(Long roleId)
    {
        return roleId != null && 1L == roleId;
    }

    @NotBlank(message = "Role name cannot be empty")
    @Size(min = 0, max = 30, message = "Role name cannot exceed 30 characters")
    public String getRoleName()
    {
        return roleName;
    }

    @NotBlank(message = "Role auth can not be empty")
    @Size(min = 0, max = 100, message = "Role auth cannot exceed 30 characters")
    public String getRoleKey()
    {
        return roleKey;
    }

    @NotNull(message = "The display order cannot be empty")
    public Integer getRoleSort()
    {
        return roleSort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("roleId", getRoleId())
                .append("roleName", getRoleName())
                .append("roleKey", getRoleKey())
                .append("roleSort", getRoleSort())
                .append("dataScope", getDataScope())
                .append("menuCheckStrictly", isMenuCheckStrictly())
                .append("deptCheckStrictly", isDeptCheckStrictly())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}

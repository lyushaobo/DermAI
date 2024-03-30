package com.dermai.project.system.domain;

import com.dermai.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * sys_user
 *
 * @author Shaobo
 */
public class SysUser extends BaseEntity {
    /* User ID */
    @Setter
    @Getter
    private Long userId;
    /* Department ID */
    @Setter
    @Getter
    private Long deptId;
    /* User Name */
    @Setter
    private String userName;
    /* User Nick Name */
    @Setter
    private String nickName;
    /* User Type (00: System User) */
    private String userType;
    /* Email */
    @Setter
    private String email;
    /* Phone Number*/
    @Setter
    private String phonenumber;
    /* Sex */
    @Setter
    @Getter
    private String sex;
    /* User Avatar */
    @Setter
    @Getter
    private String avatar;
    /* User Password */
    @Setter
    @Getter
    private String password;
    /* User Status (0 Normal 1 Deactivated) */
    @Setter
    @Getter
    private String status;
    /* Delete flag (0 presence 2 deletion)*/
    @Setter
    @Getter
    private String delFlag;
    /* Last Login IP*/
    @Setter
    @Getter
    private String loginIp;
    /* Last Login Date*/
    @Setter
    @Getter
    private Date loginDate;

    /* Department Object*/
    @Setter
    @Getter
    private SysDept dept;

    /** Roles List */
    @Setter
    @Getter
    private List<SysRole> roles;

    /** Role Ids */
    @Setter
    @Getter
    private Long[] roleIds;

    /** Post Ids */
    @Setter
    @Getter
    private Long[] postIds;

    /** Role id */
    @Getter
    @Setter
    private Long roleId;

    public SysUser()
    {

    }

    public SysUser(Long userId)
    {
        this.userId = userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    @Size(min = 0, max = 30, message = "User nicknames cannot exceed 30 characters")
    public String getNickName()
    {
        return nickName;
    }

    @NotBlank(message = "User account cannot be empty")
    @Size(min = 0, max = 30, message = "User account cannot exceed 30 characters")
    public String getUserName()
    {
        return userName;
    }

    @Email(message = "Incorrect email format")
    @Size(min = 0, max = 50, message = "Email cannot exceed 50 characters")
    public String getEmail()
    {
        return email;
    }

    @Size(min = 0, max = 11, message = "Phone number cannot exceed 11 characters")
    public String getPhonenumber()
    {
        return phonenumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("deptId", getDeptId())
                .append("userName", getUserName())
                .append("nickName", getNickName())
                .append("email", getEmail())
                .append("phonenumber", getPhonenumber())
                .append("sex", getSex())
                .append("avatar", getAvatar())
                .append("password", getPassword())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("loginIp", getLoginIp())
                .append("loginDate", getLoginDate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("dept", getDept())
                .toString();
    }
}

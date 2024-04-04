package com.dermai.project.system.domain;

import com.dermai.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Routers menu
 *
 * @author Shaobo
 */
@Setter
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Menu ID */
    @Getter
    private Long menuId;

    /** Menu Name */
    private String menuName;

    /** Parent Menu Name */
    @Getter
    private String parentName;

    /** Parent Menu Id */
    @Getter
    private Long parentId;

    /** Display Order Numder */
    private Integer orderNum;

    /** Router path */
    private String path;

    /** component path */
    private String component;

    /** router query params */
    @Getter
    private String query;

    /** Is it an external link */
    @Getter
    private String isFrame;

    /** Whether cached (0 cached 1 not cached) */
    @Getter
    private String isCache;

    /** Type（M category C menu F button） */
    private String menuType;

    /** Display status (0 displayed 1 hidden) */
    @Getter
    private String visible;

    /** Menu status (0 normal 1 deactivated) */
    @Getter
    private String status;

    /** permission string */
    private String perms;

    /** icon url */
    @Getter
    private String icon;

    /** children menus */
    @Getter
    private List<SysMenu> children = new ArrayList<SysMenu>();

    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    public String getMenuName()
    {
        return menuName;
    }

    @NotNull(message = "显示顺序不能为空")
    public Integer getOrderNum()
    {
        return orderNum;
    }

    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    public String getPath()
    {
        return path;
    }

    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    public String getComponent()
    {
        return component;
    }

    @NotBlank(message = "菜单类型不能为空")
    public String getMenuType()
    {
        return menuType;
    }

    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    public String getPerms()
    {
        return perms;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("menuId", getMenuId())
                .append("menuName", getMenuName())
                .append("parentId", getParentId())
                .append("orderNum", getOrderNum())
                .append("path", getPath())
                .append("component", getComponent())
                .append("isFrame", getIsFrame())
                .append("IsCache", getIsCache())
                .append("menuType", getMenuType())
                .append("visible", getVisible())
                .append("status ", getStatus())
                .append("perms", getPerms())
                .append("icon", getIcon())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}

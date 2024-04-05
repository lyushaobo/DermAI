package com.dermai.project.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Router VO
 *
 * @author Shaobo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo
{
    /**
     * router name
     */
    private String name;

    /**
     * router path
     */
    private String path;

    /**
     * is hidden
     */
    private boolean hidden;

    /**
     * Redirect address, when set to noRedirect the route is not clickable
     */
    private String redirect;

    /**
     * component path
     */
    private String component;

    /**
     * router query params
     */
    private String query;

    /**
     * automatically becomes a nested pattern - such as a component page
     */
    private Boolean alwaysShow;

    /**
     * others
     */
    private MetaVo meta;

    /**
     * chilren routers
     */
    private List<RouterVo> children;
}

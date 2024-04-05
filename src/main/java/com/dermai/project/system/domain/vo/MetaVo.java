package com.dermai.project.system.domain.vo;

import cn.hutool.core.util.StrUtil;
import com.dermai.common.constants.Constants;
import lombok.Data;

/**
 * other info vo
 *
 * @author Shaobo
 */
@Data
public class MetaVo
{
    /**
     * title
     */
    private String title;

    /**
     * icon@src/assets/icons/svg
     */
    private String icon;

    /**
     * if true, then it will not be cached by <keep-alive>
     */
    private boolean noCache;

    /**
     * link address
     */
    private String link;

    public MetaVo()
    {
    }

    public MetaVo(String title, String icon)
    {
        this.title = title;
        this.icon = icon;
    }

    public MetaVo(String title, String icon, boolean noCache)
    {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }

    public MetaVo(String title, String icon, String link)
    {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }

    public MetaVo(String title, String icon, boolean noCache, String link)
    {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
        if (StrUtil.startWithAny(link, Constants.HTTP, Constants.HTTPS))
        {
            this.link = link;
        }
    }
}


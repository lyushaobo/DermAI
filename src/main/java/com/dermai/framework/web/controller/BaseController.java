package com.dermai.framework.web.controller;

import cn.hutool.core.util.StrUtil;
import com.dermai.common.Utils.DateUtils;
import com.dermai.common.Utils.ServletUtils;
import com.dermai.common.core.text.Convert;
import com.dermai.framework.web.domain.AjaxResult;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * base controller
 *
 * @author Shaobo
 */
public class BaseController {
    public static final String PAGE_NUM = "pageNum";

    public static final String PAGE_SIZE = "pageSize";

    public static final String ORDER_BY_COLUMN = "orderByColumn";

    public static final String IS_ASC = "isAsc";
    private static final String REASONABLE = "reasonable";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    public void startPage() {
        HttpServletRequest request = ServletUtils.getRequest();
        Integer pageNum = Convert.toInt(request.getParameter(PAGE_NUM), 1);
        Integer pageSize = Convert.toInt(request.getParameter(PAGE_SIZE), 10);
        String orderByColumn = request.getParameter(ORDER_BY_COLUMN);
        String isAsc = request.getParameter(IS_ASC);
        String orderBy = createOrderBy(orderByColumn, isAsc);
        Boolean reasonable = Convert.toBool(request.getParameter(REASONABLE));
        if (reasonable == null) {
            reasonable = true;
        }
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);

    }

    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    private String createOrderBy(String orderByColumn, String isAsc) {
        String order = "asc";
        if (StrUtil.isNotEmpty(isAsc)) {
            // Compatible with front-end sort type
            if ("ascending".equals(isAsc)) {
                order = "asc";
            } else if ("descending".equals(isAsc)) {
                order = "desc";
            }
        }
        if (StrUtil.isEmpty(orderByColumn)) {
            return "";
        }
        return toUnderScoreCase(orderByColumn) + " " + order;
    }

    private String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // Whether the leading character is uppercase or not
        boolean preCharIsUpperCase = true;
        // Whether the current character is uppercase or not
        boolean curreCharIsUpperCase = true;
        // Whether the next character is uppercase or not
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append("_");
            } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }
}

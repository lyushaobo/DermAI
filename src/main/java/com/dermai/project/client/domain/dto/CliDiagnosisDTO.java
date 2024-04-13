package com.dermai.project.client.domain.dto;

import com.dermai.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Shaobo
 */
@Getter
@Setter
public class CliDiagnosisDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userName;
    private String dermatologistChecked;
    private Date createTime;

    @Override
    public String toString() {
        return "CliDiagnosisDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", dermatologistChecked='" + dermatologistChecked + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

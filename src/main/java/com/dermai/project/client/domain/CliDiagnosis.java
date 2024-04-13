package com.dermai.project.client.domain;

import com.dermai.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Shaobo
 */
@Getter
@Setter
public class CliDiagnosis extends BaseEntity {

    private String diagnosisId;
    private Long userId;
    private String userName;
    private String diagnosisImage;
    private String cliDescription;
    private String dermaiResult;
    private String dermatologistChecked;
    private String dermatologistDiagnosis;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    @Override
    public String toString() {
        return "CliDiagnosis{" +
                "diagnosisId='" + diagnosisId + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", diagnosisImage='" + diagnosisImage + '\'' +
                ", cliDescription='" + cliDescription + '\'' +
                ", dermaiResult='" + dermaiResult + '\'' +
                ", dermatologistChecked='" + dermatologistChecked + '\'' +
                ", dermatologistDiagnosis='" + dermatologistDiagnosis + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}

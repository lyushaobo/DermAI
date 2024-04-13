package com.dermai.project.client.service;

import com.dermai.project.client.domain.CliDiagnosis;
import com.dermai.project.client.domain.dto.CliDiagnosisDTO;

import java.util.List;

/**
 * @author Shaobo
 */
public interface ICliDiagnosisService {
    /**
     * get diagnosis history
     * @param cliDiagnosisDTO request params
     * @return list
     */
    List<CliDiagnosis> selectDiagnosisList(CliDiagnosisDTO cliDiagnosisDTO);

}

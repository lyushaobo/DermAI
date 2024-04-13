package com.dermai.project.client.service.impl;

import com.dermai.project.client.domain.CliDiagnosis;
import com.dermai.project.client.domain.dto.CliDiagnosisDTO;
import com.dermai.project.client.mapper.CliDiagnosisMapper;
import com.dermai.project.client.service.ICliDiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shaobo
 */
@Service
public class CliDiagnosisServiceImpl implements ICliDiagnosisService {
    @Autowired
    private CliDiagnosisMapper diagnosisMapper;

    @Override
    public List<CliDiagnosis> selectDiagnosisList(CliDiagnosisDTO cliDiagnosisDTO) {
        return diagnosisMapper.selectDiagnosisList(cliDiagnosisDTO);
    }
}

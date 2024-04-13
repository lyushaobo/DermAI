package com.dermai.project.client.mapper;

import com.dermai.project.client.domain.CliDiagnosis;
import com.dermai.project.client.domain.dto.CliDiagnosisDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Shaobo
 */
@Mapper
public interface CliDiagnosisMapper {
    List<CliDiagnosis> selectDiagnosisList(CliDiagnosisDTO cliDiagnosisDTO);
}

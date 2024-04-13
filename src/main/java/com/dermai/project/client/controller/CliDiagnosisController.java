package com.dermai.project.client.controller;

import com.dermai.framework.web.controller.BaseController;
import com.dermai.framework.web.domain.AjaxResult;
import com.dermai.project.client.domain.CliDiagnosis;
import com.dermai.project.client.domain.dto.CliDiagnosisDTO;
import com.dermai.project.client.service.ICliDiagnosisService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shaobo
 */
@RestController
@RequestMapping("/client/diagnosis")
public class CliDiagnosisController extends BaseController {
    @Autowired
    private ICliDiagnosisService diagnosisService;

    @ApiOperation("get diagnosis list")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermi('client:diagnosis:list')")
    public AjaxResult list(CliDiagnosisDTO cliDiagnosisDTO) {
        startPage();
        List<CliDiagnosis> diagnosisList = diagnosisService.selectDiagnosisList(cliDiagnosisDTO);
        PageInfo<CliDiagnosis> cliDiagnosisPageInfo = new PageInfo<>(diagnosisList);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("rows", cliDiagnosisPageInfo.getList());
        ajaxResult.put("total", cliDiagnosisPageInfo.getTotal());
        return ajaxResult;
    }
}

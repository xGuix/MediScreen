package com.mediscreen.patient.proxy;

import com.mediscreen.patient.dto.ReportDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="assessment" , url="localhost:8082")
public interface AssessmentProxy {
    /**
     * Get report of patient by id.
     */
    @RequestMapping("/assessment/report/id")
    ReportDto getReportByPatientId(@RequestParam Long patientId);
}

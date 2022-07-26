package com.mediscreen.patient.proxy;

import com.mediscreen.patient.dto.ReportDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Proxy Assessment Class
 */
@FeignClient(value="assessment" , url="${mediscreen.microservice-assessment}")
public interface AssessmentProxy {
    /**
     * Get report of patient by id.
     * @param patientId The patient id
     * @return ReportDto the patient report
     */
    @RequestMapping("/assessment/report/id")
    ReportDto getReportByPatientId(@RequestParam Long patientId);
}
package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.ReportDto;
import com.mediscreen.patient.proxy.AssessmentProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Main patient-app
 * Service Assessment Class via proxy assessment
 */
@Service("AssessmentService")
public class AssessmentService {
    private static Logger logger = LogManager.getLogger(AssessmentService.class);

    @Autowired
    AssessmentProxy assessmentProxy;

    /**
     * Request patient report from Assessment
     * @param patientId Long The patient id
     * @return patientNotes PatientNoteDto The patient notes list
     */
    public ReportDto getReportByPatientId(Long patientId) {
        logger.info("Get all patients notes list");
        ReportDto patientReport = assessmentProxy.getReportByPatientId(patientId);
        return patientReport;
    }
}
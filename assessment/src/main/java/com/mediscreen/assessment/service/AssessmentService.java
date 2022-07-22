package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.PatientDto;
import com.mediscreen.assessment.dto.PatientNoteDto;
import com.mediscreen.assessment.enums.RiskLevel;
import com.mediscreen.assessment.model.Report;
import com.mediscreen.assessment.proxy.NoteProxy;
import com.mediscreen.assessment.proxy.PatientProxy;
import com.mediscreen.assessment.utils.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class AssessmentService {

    private final NoteProxy noteProxy;
    private final PatientProxy patientProxy;
    private final Calculator calculator;

    @Autowired
    public AssessmentService(NoteProxy noteProxy, PatientProxy patientProxy,Calculator calculator) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
        this.calculator = calculator;
    }

    public Report generateReport (Long patientId) {
        try {
            PatientDto patient = patientProxy.patientById(patientId);
            List<PatientNoteDto> notes = noteProxy.getAllPatientNotesWithPatientId(patientId);

            Date date = patient.getBirthdate();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Instant instant = date.toInstant();
            int age = Math.toIntExact(calculator.calculateAge(instant.atZone(defaultZoneId).toLocalDate()));
            Character gender = patient.getGender();
            int triggersTermsOccurrences = calculator.calculateStringOccurrence(notes);
            String riskLevel = null;

            Report report = new Report();
            report.setPatient(patient);
            report.setAge(age);

            if ((triggersTermsOccurrences >= 0 && triggersTermsOccurrences <= 1 && age > 30)
                    || (gender=='M' && triggersTermsOccurrences >= 0 && triggersTermsOccurrences <= 2 && age < 30)
                    || (gender=='F' && triggersTermsOccurrences >= 0 && triggersTermsOccurrences <= 3 && age < 30)) {
                riskLevel = RiskLevel.NONE.toString();

            } else if (triggersTermsOccurrences >= 2 && triggersTermsOccurrences < 6 && age > 30) {
                riskLevel = RiskLevel.BORDERLINE.toString();

            } else if ((age < 30 && gender == 'M' && triggersTermsOccurrences >= 3 && triggersTermsOccurrences < 5)
                    || (age < 30 && gender == 'F' && triggersTermsOccurrences >= 4 && triggersTermsOccurrences < 7)
                    || (age > 30 && triggersTermsOccurrences >= 6 && triggersTermsOccurrences < 8)) {
                riskLevel = RiskLevel.IN_DANGER.toString();

            } else if ((age < 30 && gender == 'M' && triggersTermsOccurrences >= 5)
                    || (age < 30 && gender == 'F' && triggersTermsOccurrences >= 7)
                    || (age > 30 && triggersTermsOccurrences >= 8)) {
                riskLevel = RiskLevel.EARLY_ONSET.toString();
            }

            report.setRiskLevel(riskLevel);
            return report;
        } catch (Exception e) {
            return null;
        }
    }
}
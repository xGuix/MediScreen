package com.mediscreen.assessment.utils;

import com.mediscreen.assessment.dto.PatientNoteDto;
import com.mediscreen.assessment.enums.TriggerTerms;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Service
public class Calculator {

    /**
     * Calculate a person's age from their birthDate.
     * @param birthdate the patient birthdate
     * @return int person's age.
     */
    public int calculateAge(final Date birthdate) {
        LocalDate birthDateLocalDate = new java.sql.Date(birthdate.getTime()).toLocalDate();
        return Period.between(birthDateLocalDate, LocalDate.now()).getYears();
    }

    /**
     * Count occurrences with enum triggerTerms.
     * @param notes the patient notes list
     * @return count int occurrences number
     */
    public int calculateStringOccurrence(List<PatientNoteDto> notes) {
        int count = 0;
        StringBuilder notesToString = new StringBuilder();
        for (PatientNoteDto n : notes) {
            notesToString.append(n.getNote());
        }
        for (TriggerTerms t : TriggerTerms.values())
            if (notesToString.toString().toLowerCase().contains(t.toString())) {
                count++;
            }
        return count;
    }
}
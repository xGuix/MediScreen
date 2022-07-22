package com.mediscreen.assessment.utils;

import com.mediscreen.assessment.dto.PatientNoteDto;
import com.mediscreen.assessment.enums.TriggerTerms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class Calculator {
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    /**
     * Calculate a person's age from their birthDate.
     *
     * @param birthDate the birthdate of the person.
     * @return person's age.
     */
    public long calculateAge(final LocalDate birthDate) {

        LocalDate end = LocalDate.now(ZoneId.systemDefault());

        if (birthDate != null && birthDate.isBefore(end)) {
            logger.debug("Person age successfully calculated - value = {}", ChronoUnit.YEARS.between(birthDate, end));
            return ChronoUnit.YEARS.between(birthDate, end);
        } else {
            logger.error("Invalid start date", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }

    }

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
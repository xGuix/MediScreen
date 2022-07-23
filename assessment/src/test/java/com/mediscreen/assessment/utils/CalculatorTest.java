package com.mediscreen.assessment.utils;

import com.mediscreen.assessment.dto.PatientNoteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculatorTest {

    @Autowired
    Calculator calculator;

    @Test
    void calculateStringOccurrenceTest() {
        List<PatientNoteDto> patientNotes = new ArrayList<>();
        PatientNoteDto note1 = new PatientNoteDto();
        PatientNoteDto note2 = new PatientNoteDto();
        note1.setNote("Hémoglobine A1C pour les test");
        note2.setNote("Encore un test taille");
        patientNotes.add(note1);
        patientNotes.add(note2);
        int count = calculator.calculateStringOccurrence(patientNotes);
        assertEquals(2, count);
    }

    @Test
    void calculateStringOccurrence_SameTrigger4TimesTest() {
        List<PatientNoteDto> patientNotes = new ArrayList<>();
        PatientNoteDto note1 = new PatientNoteDto();
        PatientNoteDto note2 = new PatientNoteDto();
        note1.setNote("taille pour les tests Taille");
        note2.setNote("Taille Encore un test taille");
        patientNotes.add(note1);
        patientNotes.add(note2);
        int count = calculator.calculateStringOccurrence(patientNotes);
        assertEquals(1, count);
    }

    @Test
    void calculateStringOccurrence_NoTriggerTest() {
        List<PatientNoteDto> patientNotes = new ArrayList<>();
        PatientNoteDto note1 = new PatientNoteDto();
        PatientNoteDto note2 = new PatientNoteDto();
        note1.setNote("Rien à compter pour ce tests");
        note2.setNote("Ici encore rien de plus");
        patientNotes.add(note1);
        patientNotes.add(note2);
        int count = calculator.calculateStringOccurrence(patientNotes);
        assertEquals(0, count);
    }

    @Test
    void calculateStringOccurrence_MixedUpperCaseLowerCaseTriggerTermsTest() {
        List<PatientNoteDto> patientNotes = new ArrayList<>();
        PatientNoteDto note1 = new PatientNoteDto();
        PatientNoteDto note2 = new PatientNoteDto();
        note1.setNote("MICROalBUMinE pour les tests");
        note2.setNote("Encore un test fUMeUR");
        patientNotes.add(note1);
        patientNotes.add(note2);
        int count = calculator.calculateStringOccurrence(patientNotes);
        assertEquals(2, count);
    }

    @Test
    void calculateAgeTest() {
        Date date = Date.valueOf("2000-12-25");
        int age = calculator.calculateAge(date);
        assertEquals(21, age);
    }
}

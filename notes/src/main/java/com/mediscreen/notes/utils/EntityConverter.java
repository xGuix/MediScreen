package com.mediscreen.notes.utils;

import com.mediscreen.notes.dto.NoteFormDto;
import com.mediscreen.notes.model.PatientNote;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
    public PatientNote dtoToPatientNote(NoteFormDto noteDto) {
        PatientNote note = new PatientNote();
        note.setPatientId(Long.valueOf(noteDto.getPatId())/*.longValue()*/);
        note.setNote(noteDto.getE());
        return note;
    }
}
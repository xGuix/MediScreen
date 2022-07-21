package com.mediscreen.notes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.notes.model.PatientNote;
import com.mediscreen.notes.service.PatientNotesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientNotesController.class)
public class PatientNoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PatientNotesService patientNotesService;

    static PatientNote patientNote;
    static List<PatientNote> patientNotesList;
    static Model model;

    @BeforeAll
    static void setupTest(){
        patientNotesList = new ArrayList<>();
        patientNote = new PatientNote("62d12c25191bcc3f11d08547", 1L, "Something that can be set as few notes for test", Date.valueOf("2001-12-25"));
        patientNotesList.add(patientNote);
    }

    @Test
    void getAllPatientNotesWithPatientIdTest() throws Exception
    {
        when(patientNotesService.getAllPatientNotesWithPatientId(any())).thenReturn(patientNotesList);
        mockMvc.perform(get("/notes/patientNotes")
                        .param("patientId", objectMapper.writeValueAsString(patientNote.getPatientId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patientNotesList)));
    }

    @Test
    void getPatientNoteWithIdTest() throws Exception
    {
        when(patientNotesService.getPatientNotesWithId(any())).thenReturn(patientNote);
        mockMvc.perform(get("/notes/patientNotes/id")
                        .param("id", objectMapper.writeValueAsString(patientNote.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patientNote)));
    }

    @Test
    void addNewNoteTest() throws Exception
    {
        PatientNote patientNote2;
        patientNote2 = new PatientNote("test000id000Patient56", 1L, "Something that can be set as few notes for test", Date.valueOf("2010-12-25"));
        when(patientNotesService.addNewNote(any(),any())).thenReturn(patientNote2);
        mockMvc.perform(post("/notes/patientNotes/add")
                        .param("note", objectMapper.writeValueAsString(patientNote2.getNote()))
                        .param("patientId", objectMapper.writeValueAsString(patientNote2.getPatientId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(patientNote2)));
    }

    @Test
    void updateNoteTest() throws Exception
    {
        PatientNote patientNote2;
        patientNote2 = new PatientNote("test000id000Patient56", 1L, "Something that can be change for test setting", Date.valueOf("2010-12-25"));
        when(patientNotesService.updateNote(any())).thenReturn(patientNote2);
        mockMvc.perform(put("/notes/patientNotes/update")
                        .content(objectMapper.writeValueAsString(patientNote2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patientNote2)));
    }

    @Test
    void deleteNoteTest() throws Exception
    {
        mockMvc.perform(delete("/notes/patientNotes/delete")
                        .param("id",objectMapper.writeValueAsString(patientNote.getPatientId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
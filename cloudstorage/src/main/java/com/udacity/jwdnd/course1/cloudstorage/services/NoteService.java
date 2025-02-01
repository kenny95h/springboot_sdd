package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void addNewNote(NoteForm noteForm) {
        noteMapper.addNote(noteForm);
    }

    public List<Note> getNotes(Integer userid) {
        return noteMapper.getNotesByUserId(userid);
    }

    public void deleteNote(Integer noteid) {
        noteMapper.deleteNote(noteid);
    }

    public void updateNote(NoteForm noteForm) { noteMapper.updateNote(noteForm); }

}

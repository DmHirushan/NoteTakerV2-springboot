package lk.ijse.gdse.aad68.NoteTakerV2.service;

import lk.ijse.gdse.aad68.NoteTakerV2.customObj.NoteResponse;
import lk.ijse.gdse.aad68.NoteTakerV2.dto.impl.NoteDTO;

import java.util.List;

public interface NoteService {
    void saveNote(NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    void deleteNote(String noteId);
    NoteResponse getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}

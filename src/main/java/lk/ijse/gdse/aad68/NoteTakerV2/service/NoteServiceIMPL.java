package lk.ijse.gdse.aad68.NoteTakerV2.service;

import lk.ijse.gdse.aad68.NoteTakerV2.customObj.NoteErrorResponse;
import lk.ijse.gdse.aad68.NoteTakerV2.customObj.NoteResponse;
import lk.ijse.gdse.aad68.NoteTakerV2.dao.NoteDao;
import lk.ijse.gdse.aad68.NoteTakerV2.dto.impl.NoteDTO;
import lk.ijse.gdse.aad68.NoteTakerV2.entity.NoteEntity;
import lk.ijse.gdse.aad68.NoteTakerV2.exception.DataPersistFailedException;
import lk.ijse.gdse.aad68.NoteTakerV2.exception.NoteNotFoundException;
import lk.ijse.gdse.aad68.NoteTakerV2.util.AppUtil;
import lk.ijse.gdse.aad68.NoteTakerV2.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public  class NoteServiceIMPL implements NoteService {
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNoteId());
        NoteEntity savedNote = noteDao.save(mapping.convertToEntity(noteDTO));
        if (savedNote == null && savedNote.getNoteId() == null){
            throw new DataPersistFailedException("Can't save the note!");
        }
    }

    @Override
    public void updateNote(String noteId, NoteDTO incomeNoteDTO) {
        Optional<NoteEntity> tmpNoteEntityById = noteDao.findById(noteId);
        if (!tmpNoteEntityById.isPresent()){
            throw new NoteNotFoundException("Note not found!");
        }else {
            tmpNoteEntityById.get().setNoteDesc(incomeNoteDTO.getNoteDesc());
            tmpNoteEntityById.get().setCreateDate(incomeNoteDTO.getCreateDate());
            tmpNoteEntityById.get().setNoteTitle(incomeNoteDTO.getNoteTitle());
            tmpNoteEntityById.get().setPriorityLevel(incomeNoteDTO.getPriorityLevel());
        }
    }

    @Override
    public void deleteNote(String noteId) {
        Optional<NoteEntity> selectedNote = noteDao.findById(noteId);
        if (!selectedNote.isPresent()){
            throw new NoteNotFoundException("Note Not Found!");
        }else {
            noteDao.deleteById(noteId);
        }
    }

    @Override
    public NoteResponse getSelectedNote(String noteId) {
        if (noteDao.existsById(noteId)){
            return mapping.convertToDTO(noteDao.getNoteEntitiesByNoteId(noteId));
        }else {
            return new NoteErrorResponse(0, "Note not found!");
        }
    }

    @Override
    public List<NoteDTO> getAllNotes() {
      return mapping.convertToDTO(noteDao.findAll());
    }
}

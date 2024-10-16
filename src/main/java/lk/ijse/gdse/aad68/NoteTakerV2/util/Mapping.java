package lk.ijse.gdse.aad68.NoteTakerV2.util;

import lk.ijse.gdse.aad68.NoteTakerV2.dto.impl.NoteDTO;
import lk.ijse.gdse.aad68.NoteTakerV2.dto.impl.UserDto;
import lk.ijse.gdse.aad68.NoteTakerV2.entity.NoteEntity;
import lk.ijse.gdse.aad68.NoteTakerV2.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //matters of NoteEntity and DTO
    public NoteDTO convertToDTO(NoteEntity note) {
        return modelMapper.map(note, NoteDTO.class);
    }
    public NoteEntity convertToEntity(NoteDTO dto) {
        return modelMapper.map(dto, NoteEntity.class);
    }
    public List<NoteDTO> convertToDTO(List<NoteEntity> notes) {
        return modelMapper.map(notes, new TypeToken<List<NoteDTO>>(){}.getType());
    }

    //user matters mapping
    public UserDto convertToUserDTO(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity convertToEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }

    public List<UserDto> convertToUserDTOS(List<UserEntity> users) {
        return modelMapper.map(users, new TypeToken<List<UserDto>>(){}.getType());
    }

}

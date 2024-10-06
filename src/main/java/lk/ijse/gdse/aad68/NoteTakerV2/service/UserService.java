package lk.ijse.gdse.aad68.NoteTakerV2.service;


import lk.ijse.gdse.aad68.NoteTakerV2.customObj.UserResponse;
import lk.ijse.gdse.aad68.NoteTakerV2.dto.impl.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDto> getAllUsers();
}

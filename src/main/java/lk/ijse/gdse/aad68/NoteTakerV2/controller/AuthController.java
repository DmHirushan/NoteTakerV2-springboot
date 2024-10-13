package lk.ijse.gdse.aad68.NoteTakerV2.controller;

import lk.ijse.gdse.aad68.NoteTakerV2.JWTmodels.JWTResponse;
import lk.ijse.gdse.aad68.NoteTakerV2.JWTmodels.SignIn;
import lk.ijse.gdse.aad68.NoteTakerV2.dto.impl.UserDto;
import lk.ijse.gdse.aad68.NoteTakerV2.exception.DataPersistFailedException;
import lk.ijse.gdse.aad68.NoteTakerV2.util.AppUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @PostMapping(value = "signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JWTResponse> signUp(@RequestPart("firstName") String firstName,
                                              @RequestPart("lastName") String lastName,
                                              @RequestPart("email") String email,
                                              @RequestPart("password") String password,
                                              @RequestPart("profilePic") MultipartFile profilePic){

        try{
            //Handle profile pic
            byte[] imageByteCollection = profilePic.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(imageByteCollection);
            var buildUserDto = new UserDto();
            buildUserDto.setFirstName(firstName);
            buildUserDto.setLastName(lastName);
            buildUserDto.setEmail(email);
            buildUserDto.setPassword(password);
            buildUserDto.setProfilePic(base64ProfilePic);

            userService.saveUser(buildUserDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "signin")
    public String signIn(@RequestBody SignIn signIn){
        return "signIn";
    }

}

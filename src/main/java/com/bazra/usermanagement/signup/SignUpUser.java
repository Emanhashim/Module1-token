package com.bazra.usermanagement.signup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.model.UserInfoService;
import com.bazra.usermanagement.repository.UserRepository;

import lombok.AllArgsConstructor;

/**
 * SignUp Controller
 * 
 * @author Bemnet
 * @version 4/2022
 */

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@Api(value = "Signup User Endpoint", description = "Here we take new user data inorder to register")
@ApiResponses(value ={
        @ApiResponse(code = 404, message = "web user that a requested page is not available "),
        @ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
        @ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
        @ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
        @ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})
@AllArgsConstructor

public class SignUpUser {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserRepository userRepository;

    /**
     * Handles user SignUp request
     * 
     * @param request( user input)
     * @return signup validation response
     */
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpRequest request) {
        String pass1 = request.getPassword();
        String pass2 = request.getConfirmPassword();


        if (!pass1.matches(pass2)) {
            return ResponseEntity.badRequest().body(new SignUpResponse("Error: Passwords don't match!"));
        }

        UserInfo user = new UserInfo(request.getFirstName(), request.getLastName(),
                passwordEncoder.encode(request.getPassword()), request.getUsername());
        user.setCountry(request.getCountry());
        user.setBirthday(request.getBirthDay());
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());
        
        String strgender = request.getGender();

        if (strgender.matches("MALE")) {

            user.setGender(strgender);

        } else if (strgender.matches("FEMALE")) {

            user.setGender(strgender);
        }

        return userInfoService.signUpUser(user, pass1);
    }
}

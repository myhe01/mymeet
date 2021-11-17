package com.example.demo.registration;

import com.example.demo.applicationuser.AppUserRole;
import com.example.demo.applicationuser.AppUserService;
import com.example.demo.applicationuser.UpdatedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailVerification emailVerification;

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailVerification.test(request.getUserEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(
                new UpdatedUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getUserName(),
                        request.getUserEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}

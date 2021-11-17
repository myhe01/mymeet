package com.example.demo.applicationuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return appUserRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(UpdatedUser updatedUser) {
        boolean userExists = appUserRepository
                .findByEmail(updatedUser.getUserEmail())
                .isPresent();

        // TODO: Update exception
        if(userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encryptedPassword =
                bCryptPasswordEncoder.encode(updatedUser.getPassword());

        updatedUser.setPassword(encryptedPassword);
        appUserRepository.save(updatedUser);

        // TODO: Send unique confirmation token
        return "success";

    }

}


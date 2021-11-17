package com.example.demo.applicationuser;

import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
@EqualsAndHashCode
public class UpdatedUser implements UserDetails {
    @Id
    private String userID;

    private String firstName;
    private String lastName;
    private String userName;
    private String userEmail;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked;
    private Boolean enabled;

    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @SuppressWarnings("JpaAttributeTypeInspection")
    private ArrayList<String> interestsList;

    public UpdatedUser(){}

    public UpdatedUser(String firstName, String lastName, String userName, String userEmail,
                       String password, AppUserRole appUserRole)
    {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(userName);
        this.setUserEmail(userEmail);
        this.setPassword(password);
        this.appUserRole = appUserRole;
    }

    // Getters and Setters
    public String getFirstName() {return firstName; }
    public void setFirstName(String firstName) {this.firstName = firstName; }
    public String getLastName() {return lastName; }
    public void setLastName(String lastName) {this.lastName = lastName; }
    public String getUsername() {return userName; }
    public void setUsername(String userName) {this.userName = userName; }
    public String getUserEmail() {return userEmail; }
    public void setUserEmail(String userEmail) {this.userEmail = userEmail; }
    public String getPassword() {return password; }

    public void setPassword(String password) {this.password = password; }
    public String getUserID() {return userID; }
    public void setUserID(String userID) {this.userID = userID; }
    public ArrayList<String> getInterests(){ return interestsList; }

    public void setInterests(ArrayList<String> interests)
    {
        this.interestsList = new ArrayList<String>();

        for (String s: interests) {
            interestsList.add(s);
        }
    }

    public void addInterest(String interest) {
        if (interestsList == null) {
            interestsList = new ArrayList<String>();
        }

        interestsList.add(interest);
    }
}

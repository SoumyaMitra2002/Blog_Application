package com.soumya.blog_application.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDTO {
    @NotEmpty
    @Size(min =4,message = "Name should min 4 chars")
    private String name;
    @Email(message = "Email address is not valid!")
    private String email;
    @NotEmpty
    @Size(min=4,max = 10,message = "Password should be 4 chars min and 10 max respectively!")
    private String password;
    @NotEmpty
    private String about;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    
}

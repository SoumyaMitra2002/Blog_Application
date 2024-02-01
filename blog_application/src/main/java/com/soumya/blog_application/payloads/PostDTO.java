package com.soumya.blog_application.payloads;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostDTO {
    private String title;
    private String content;
    private String imageName;

    private Date addedDate;


    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    @JsonIgnore
    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    public String getImageName() {
        return imageName;
    }
    @JsonProperty
    public Date getAddedDate() {
        return addedDate;
    }
    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }
    public UserDTO getUserDTO() {
        return userDTO;
    }
    @JsonIgnore
    private CategoryDTO categoryDTO;
    @JsonIgnore
    private UserDTO userDTO;



}

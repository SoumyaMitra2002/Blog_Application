package com.soumya.blog_application.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @Column(name = "post_title",length = 100,nullable = false)
    private String title;

    @Column(length = 10000)
    private String content;

    private String imageName;
    private Date addedDate;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    
}

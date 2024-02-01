package com.soumya.blog_application.payloads;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponse {
    private List<PostDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private long totalRecords;
    private Integer totalPages;
    private Boolean lastPage;


    
}

package com.soumya.blog_application.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDTO {
    @NotEmpty
    @Size(min=4,message = "Title should be 4 chars min")
    private String categoryTitle;

    @NotEmpty
    @Size(min=10,message = "Title should be 10 chars min")
    private String categoryDescription;
}

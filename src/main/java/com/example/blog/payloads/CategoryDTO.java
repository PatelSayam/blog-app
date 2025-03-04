package com.example.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Integer categoryId;

    @NotBlank
    @Size(min = 4, message = "title size should be min of {min} chars")
    private String categoryTitle;

    @Size(min = 10, message = "category desc should be min of {min} chars")
    private String categoryDescription;
}

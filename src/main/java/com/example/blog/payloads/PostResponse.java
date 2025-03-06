package com.example.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPage;
    private boolean lastPage;
}

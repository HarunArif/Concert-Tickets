package com.testcodekonser.testcode_konser.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PagingResponse {
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElement;

}

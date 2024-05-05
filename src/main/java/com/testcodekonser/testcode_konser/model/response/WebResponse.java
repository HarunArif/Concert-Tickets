package com.testcodekonser.testcode_konser.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class WebResponse<T> {
    private String status;
    private String message;
    private PagingResponse paging;
    private T data;

}
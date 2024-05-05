package com.testcodekonser.testcode_konser.model.response;

import lombok.*;

import java.sql.Time;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LineUpResponse {
    private String singer;
    private Time hours;
}

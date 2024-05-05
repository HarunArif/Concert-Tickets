package com.testcodekonser.testcode_konser.model.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayResponse {
    private String day;
    private Date date;
    private double price;
    private String category;
}

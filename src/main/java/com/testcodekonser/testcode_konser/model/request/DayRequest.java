package com.testcodekonser.testcode_konser.model.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayRequest {
    private String day;
    private Date date;
    private double price;
    private String category;
    private String lineUpId;
}

package com.testcodekonser.testcode_konser.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LineUpRequest {
    @NotNull(message = "Hours is mandatory")
    private Time hours;

    @NotBlank(message = "Penyanyi is mandatory and cannot be blank")
    private String singer;


}

package com.testcodekonser.testcode_konser.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="m_lineup")
public class LineUp {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Time hours;
    private String singer;

}

package com.testcodekonser.testcode_konser.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="m_day")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String day;
    @Column(nullable = false,updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "LineUp_id")
    private LineUp lineUp;

    private double price;
    private String category;
}

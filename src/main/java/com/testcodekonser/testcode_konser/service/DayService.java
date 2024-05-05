package com.testcodekonser.testcode_konser.service;

import com.testcodekonser.testcode_konser.entity.Customer;
import com.testcodekonser.testcode_konser.entity.Day;
import com.testcodekonser.testcode_konser.model.request.CustomerRequest;
import com.testcodekonser.testcode_konser.model.request.DayRequest;
import com.testcodekonser.testcode_konser.model.response.CustomerResponse;
import com.testcodekonser.testcode_konser.model.response.DayResponse;
import org.springframework.data.domain.Page;

public interface DayService {
    DayResponse createDay(DayRequest dayRequest);

    Page<Day> getAllDay(Integer page, Integer size);

    Day getDayById(String id);

    Day updateDay(Day day);

    void  deleteDayById(String id);
}

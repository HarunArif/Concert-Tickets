package com.testcodekonser.testcode_konser.service;

import com.testcodekonser.testcode_konser.entity.Customer;
import com.testcodekonser.testcode_konser.entity.LineUp;
import com.testcodekonser.testcode_konser.model.request.LineUpRequest;
import com.testcodekonser.testcode_konser.model.response.LineUpResponse;
import org.springframework.data.domain.Page;

public interface LineUpService {
    LineUpResponse createLineUp(LineUpRequest lineUpRequest);

    Page<LineUp> getAllLineUp(Integer page, Integer size);

    LineUp getLineUpById(String id);


    LineUp updateLineUp(LineUp lineUp);

    void  deleteLineUpById(String id);


}

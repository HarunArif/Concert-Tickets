package com.testcodekonser.testcode_konser.service.Impl;

import com.testcodekonser.testcode_konser.entity.Customer;
import com.testcodekonser.testcode_konser.entity.LineUp;
import com.testcodekonser.testcode_konser.model.request.LineUpRequest;
import com.testcodekonser.testcode_konser.model.response.LineUpResponse;
import com.testcodekonser.testcode_konser.repository.LineUpRepository;
import com.testcodekonser.testcode_konser.service.LineUpService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LineUpServiceImpl implements LineUpService {
    private final LineUpRepository lineUpRepository;
    @Override
    public LineUpResponse createLineUp(LineUpRequest lineUpRequest) {
        LineUp newLineUp = LineUp.builder()
                .hours(lineUpRequest.getHours())
                .singer(lineUpRequest.getSinger())
                .build();
        LineUp registLineUp = lineUpRepository.saveAndFlush(newLineUp);
        return LineUpResponse.builder()
                .hours(registLineUp.getHours())
                .singer(registLineUp.getSinger())
                .build();
    }

    @Override
    public Page<LineUp> getAllLineUp(Integer page, Integer size) {
        if (page <=0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page-1, size);
        return lineUpRepository.findAll(pageable);
    }

    @Override
    public LineUp getLineUpById(String id) {
        Optional<LineUp> optionalLineUp = lineUpRepository.findById(id);
        if (optionalLineUp.isPresent()) return optionalLineUp.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id : " + id + " Not Found");
    }

    @Override
    public LineUp updateLineUp(LineUp lineUp) {
        this.getLineUpById(lineUp.getId());
        return lineUpRepository.saveAndFlush(lineUp);
    }

    @Override
    public void deleteLineUpById(String id) {
        this.getLineUpById(id);
        lineUpRepository.deleteById(id);
    }
}

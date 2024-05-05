package com.testcodekonser.testcode_konser.service.Impl;

import com.testcodekonser.testcode_konser.entity.Day;
import com.testcodekonser.testcode_konser.model.request.DayRequest;
import com.testcodekonser.testcode_konser.model.response.DayResponse;
import com.testcodekonser.testcode_konser.repository.DayRepository;
import com.testcodekonser.testcode_konser.service.DayService;
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
public class DayServiceImpl implements DayService {
    private final DayRepository dayRepository;
    @Override
    public DayResponse createDay(DayRequest dayRequest) {
        Day newDay = Day.builder()
                .day(dayRequest.getDay())
                .date(dayRequest.getDate())
                .category(dayRequest.getCategory())
                .price(dayRequest.getPrice())
                .build();
        Day registDay = dayRepository.saveAndFlush(newDay);
        return DayResponse.builder()
                .day(registDay.getDay())
                .date(registDay.getDate())
                .category(registDay.getCategory())
                .price(registDay.getPrice())
                .build();
    }

    @Override
    public Page<Day> getAllDay(Integer page, Integer size) {
        if (page <= 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        return dayRepository.findAll(pageable);
    }

    @Override
    public Day getDayById(String id) {
        Optional<Day> optionalDay = dayRepository.findById(id);
        if (optionalDay.isPresent()) return optionalDay.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Day with id : " + id + " Not Found");
    }

    @Override
    public Day updateDay(Day day) {
        this.getDayById(day.getId());
        return dayRepository.save(day);
    }

    @Override
    public void deleteDayById(String id) {
        this.getDayById(id);
        dayRepository.deleteById(id);
    }
}

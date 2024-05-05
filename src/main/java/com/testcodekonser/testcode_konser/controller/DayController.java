package com.testcodekonser.testcode_konser.controller;

import com.testcodekonser.testcode_konser.entity.Day;
import com.testcodekonser.testcode_konser.entity.LineUp;
import com.testcodekonser.testcode_konser.model.request.DayRequest;
import com.testcodekonser.testcode_konser.model.response.DayResponse;
import com.testcodekonser.testcode_konser.model.response.PagingResponse;
import com.testcodekonser.testcode_konser.model.response.WebResponse;
import com.testcodekonser.testcode_konser.service.DayService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/day")
@AllArgsConstructor
public class DayController {
    private final DayService dayService;
    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<?> createDay(@Valid @RequestBody DayRequest dayRequest) {
        DayResponse newDay = dayService.createDay(dayRequest);
        WebResponse<DayResponse> response = WebResponse.<DayResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success add Day")
                .data(newDay)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllDay(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Page<Day> dayList = dayService.getAllDay(page, size);

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(page).size(size)
                .totalPages(dayList.getTotalPages())
                .totalElement(dayList.getTotalElements())
                .build();

        WebResponse<List<Day>> response = WebResponse.<List<Day>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get List data")
                .paging(pagingResponse)
                .data(dayList.getContent())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getDayUpById(@PathVariable String id){
        Day findDay = dayService.getDayById(id);
        WebResponse<Day> response = WebResponse.<Day>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get Day By Id")
                .data(findDay)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<?> deleteDayById(@PathVariable String id){
        dayService.deleteDayById(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Delete Day By Id ")
                .data("OK")
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<?> updateDayById(@RequestBody Day day){
        Day updateDay = dayService.updateDay(day);
        WebResponse<Day> response = WebResponse.<Day>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Update Day By Id ")
                .data(updateDay)
                .build();
        return ResponseEntity.ok(response);
    }
}

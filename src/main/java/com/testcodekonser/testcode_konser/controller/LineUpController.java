package com.testcodekonser.testcode_konser.controller;

import com.testcodekonser.testcode_konser.entity.Customer;
import com.testcodekonser.testcode_konser.entity.LineUp;
import com.testcodekonser.testcode_konser.model.request.CustomerRequest;
import com.testcodekonser.testcode_konser.model.request.LineUpRequest;
import com.testcodekonser.testcode_konser.model.response.CustomerResponse;
import com.testcodekonser.testcode_konser.model.response.LineUpResponse;
import com.testcodekonser.testcode_konser.model.response.PagingResponse;
import com.testcodekonser.testcode_konser.model.response.WebResponse;
import com.testcodekonser.testcode_konser.repository.LineUpRepository;
import com.testcodekonser.testcode_konser.service.LineUpService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Line;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lineup")
@AllArgsConstructor
public class LineUpController {
    private final LineUpService lineUpService;

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<?> createLineUp(@Valid @RequestBody LineUpRequest lineUpRequest) {
        LineUpResponse newLineUp = lineUpService.createLineUp(lineUpRequest);
        WebResponse<LineUpResponse> response = WebResponse.<LineUpResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success add LineUp")
                .data(newLineUp)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllLineUp(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Page<LineUp> lineUpList = lineUpService.getAllLineUp(page, size);

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(page).size(size)
                .totalPages(lineUpList.getTotalPages())
                .totalElement(lineUpList.getTotalElements())
                .build();

        WebResponse<List<LineUp>> response = WebResponse.<List<LineUp>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get List data")
                .paging(pagingResponse)
                .data(lineUpList.getContent())
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getLineUpById(@PathVariable String id){
        LineUp findLineUp = lineUpService.getLineUpById(id);
        WebResponse<LineUp> response = WebResponse.<LineUp>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get LineUp")
                .data(findLineUp)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<?> deleteLineUpById(@PathVariable String id){
        lineUpService.deleteLineUpById(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Delete LineUp By Id ")
                .data("OK")
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<?> updateLineUpById(@RequestBody LineUp lineUp){
        LineUp updateLineUp = lineUpService.updateLineUp(lineUp);
        WebResponse<LineUp> response = WebResponse.<LineUp>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Update LineUp By Id ")
                .data(updateLineUp)
                .build();
        return ResponseEntity.ok(response);
    }
}

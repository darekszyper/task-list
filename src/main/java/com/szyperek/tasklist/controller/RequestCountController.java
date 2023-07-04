package com.szyperek.tasklist.controller;

import com.szyperek.tasklist.util.RequestCountFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RequestCountController {
    private final RequestCountFilter requestCountFilter;

    @GetMapping("/request-count")
    public ResponseEntity<Long> getRequestCount() {
        return ResponseEntity.ok(requestCountFilter.getRequestCounter());
    }
}

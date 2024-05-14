package com.example.FORMANTO.controller;

import com.example.FORMANTO.domain.QnA;
import com.example.FORMANTO.dto.QnAQuestionSubmitRequest;
import com.example.FORMANTO.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QnAApiController {

    private final QnAService qnAService;

    @PostMapping("/api/QnAQuestion")
    public ResponseEntity<QnA> question(@RequestBody QnAQuestionSubmitRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(qnAService.save(request));
    }
}

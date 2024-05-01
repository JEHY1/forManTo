package com.example.FORMANTO.controller;

import com.example.FORMANTO.dto.AuthKeyResponse;
import com.example.FORMANTO.dto.sendAuthMailRequest;
import com.example.FORMANTO.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailApiController {

    private final MailService mailService;

    @PostMapping("/authMail")
    public ResponseEntity<AuthKeyResponse> sendAuthMail(@RequestBody sendAuthMailRequest request){

        return ResponseEntity.ok()
                .body(new AuthKeyResponse(mailService.sendMail(request.getEmail())));
    }

}

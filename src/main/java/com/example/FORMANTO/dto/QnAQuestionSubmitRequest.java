package com.example.FORMANTO.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnAQuestionSubmitRequest {

    private String question;
    private Long productGroupId;

}

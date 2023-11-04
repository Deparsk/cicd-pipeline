package com.paschal.blogTask.model.error;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorModel {
    private String code;
    private String message;
}
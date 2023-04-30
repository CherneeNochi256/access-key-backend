package com.example.accesskeybackend.template.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SuccessResponse implements Serializable {
    private final Boolean success;
}

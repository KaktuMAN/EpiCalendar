package com.epicalendar.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostModuleSubscription {
    private final String mail;
    private final String moduleCode;
    private final int year;
}

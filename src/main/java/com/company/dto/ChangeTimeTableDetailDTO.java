package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangeTimeTableDetailDTO {
    @NotBlank(message = "id required")
    private Long id;
    @NotBlank(message = "start time required")
    private String startTime;
    @NotBlank(message = "end time required")
    private String endTime;
}
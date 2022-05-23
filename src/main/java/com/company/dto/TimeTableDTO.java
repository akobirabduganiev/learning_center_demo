package com.company.dto;

import com.company.entity.DayEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeTableDTO implements Serializable {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    @NotBlank(message = "start time required")
    private String startTime;
    @NotBlank(message = "end time required")
    private String endTime;
    @NotBlank(message = "dayId required")
    private Long dayId;

    private DayEntity day;
}

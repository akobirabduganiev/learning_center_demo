package com.company.dto;

import com.company.entity.PaymentTypeEntity;
import com.company.entity.StudentEntity;
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
public class PaymentDTO {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @NotBlank(message = "sum required!")
    private Double sum;
    @NotBlank(message = "description required!")
    private String description;
    @NotBlank(message = "student id required!")
    private Long studentId;
    @NotBlank(message = "payment type id required!")
    private Long paymentTypeId;

    private StudentEntity student;
    private PaymentTypeEntity paymentType;
}

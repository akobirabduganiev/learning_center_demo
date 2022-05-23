package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangePaymentDetailDTO {
    private Long id;
    private Long studentId;
    private Long paymentTypeId;
    private Double sum;
    private String description;
}

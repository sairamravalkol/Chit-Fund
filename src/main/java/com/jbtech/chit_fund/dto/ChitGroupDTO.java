package com.jbtech.chit_fund.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChitGroupDTO {

    @NotBlank(message = "Id is required")
    private String id;

    @NotBlank(message = "Chit group name is required")
    private String chitGroupName;

    @NotNull(message = "Term is required")
    @Min(value = 1, message = "Term must be greater than 0")
    private Integer term;

    @NotNull(message = "Premium is required")
    @Min(value = 1, message = "Premium must be greater than 0")
    private Double premium;

    @NotNull(message = "Sum assured is required")
    @Min(value = 1, message = "Sum assured must be greater than 0")
    private Double sumAssured;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;


}
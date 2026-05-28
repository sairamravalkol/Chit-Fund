package com.jbtech.chit_fund.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChitGroupTemplateDTO {

    @NotBlank
    @Size(max = 100)
    private String groupId;

    @NotNull
    @Positive
    private Double premiumMonthly;

    @NotBlank
    @Size(max = 200)
    private String chitGroupName;

    @NotNull
    @Positive
    private Double sumAssured;

    @NotNull
    @Positive
    private Integer noOfMonth;

  
    private Integer version;
}

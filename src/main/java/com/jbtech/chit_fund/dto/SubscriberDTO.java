package com.jbtech.chit_fund.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriberDTO {


        @NotBlank(message = "First Name is required")
        private String firstName;

        @NotBlank(message = "Last Name is required")
        private String lastName;

        @NotBlank(message = "Father/Spouse Name is required")
        private String fatherSpouseName;

        @NotNull(message = "Phone Number1 is required")
        private Long phoneNumber1;

        private Long phoneNumber2;

        private String emailId;

        @NotBlank(message = "Address is required")
        private String address;

        private Long idProofAadharNo;

        private String occupation;

        private String incomeSource;

        private Double annualIncome;

        @NotBlank(message = "Reference Name1 is required")
        private String referenceName1;

        @NotNull(message = "Reference Phone1 is required")
        private Long referencePhone1;

        private String referenceName2;

        private Long referencePhone2;

        @NotBlank(message = "Nominee Name is required")
        private String nomineeName;

        @NotBlank(message = "Relation with Nominee is required")
        private String relationWithNominee;

        @NotNull(message = "Nominee Phone is required")
        private Long nomineePhone;

}

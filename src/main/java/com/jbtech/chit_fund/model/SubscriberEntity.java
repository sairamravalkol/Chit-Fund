package com.jbtech.chit_fund.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subscriber")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberEntity extends AuditColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "father_spouse_name", nullable = false)
    private String fatherSpouseName;

    @Column(name = "phone_number1", nullable = false)
    private Long phoneNumber1;

    @Column(name = "phone_number2")
    private Long phoneNumber2;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "address", nullable = false, length = 500)
    private String address;

    @Column(name = "id_proof_aadhar_no")
    private Long idProofAadharNo;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "income_source")
    private String incomeSource;

    @Column(name = "annual_income")
    private Double annualIncome;

    @Column(name = "reference_name1", nullable = false)
    private String referenceName1;

    @Column(name = "reference_phone1", nullable = false)
    private Long referencePhone1;

    @Column(name = "reference_name2")
    private String referenceName2;

    @Column(name = "reference_phone2")
    private Long referencePhone2;

    @Column(name = "nominee_name", nullable = false)
    private String nomineeName;

    @Column(name = "relation_with_nominee", nullable = false)
    private String relationWithNominee;

    @Column(name = "nominee_phone", nullable = false)
    private Long nomineePhone;
}
package com.michalswig.agencycrm.modules.crm.domain;

import com.michalswig.agencycrm.modules.identity.domain.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(
        name = "caregiver",
        indexes = {
                @Index(name = "ix_caregiver_last_name", columnList = "last_name"),
                @Index(name = "ix_caregiver_email", columnList = "email")
        }
)
public class Caregiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 120)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 120)
    private String lastName;

    @Column(length = 20)
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "weight_kg")
    private Integer weightKg;

    @Column(name = "height_cm")
    private Integer heightCm;

    @Column(length = 50)
    private String phone;

    @Column(length = 200)
    private String email;

    @Column(length = 80)
    private String nationality;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "has_driver_license", nullable = false)
    private boolean hasDriverLicense;

    @Column(name = "is_smoker", nullable = false)
    private boolean isSmoker;

    @Column(name = "medical_qualification_notes", columnDefinition = "TEXT")
    private String medicalQualificationNotes;

    @Column(name = "recruiter_notes", columnDefinition = "TEXT")
    private String recruiterNotes;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    protected Caregiver() {}

    public Caregiver(String firstName, String lastName, String gender, LocalDate dateOfBirth,
                     Integer weightKg, Integer heightCm, String phone, String email, String nationality,
                     Integer experienceYears, boolean hasDriverLicense, boolean isSmoker,
                     String medicalQualificationNotes, String recruiterNotes,
                     OffsetDateTime createdAt, User createdBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.weightKg = weightKg;
        this.heightCm = heightCm;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;
        this.experienceYears = experienceYears;
        this.hasDriverLicense = hasDriverLicense;
        this.isSmoker = isSmoker;
        this.medicalQualificationNotes = medicalQualificationNotes;
        this.recruiterNotes = recruiterNotes;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}

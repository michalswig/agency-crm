package com.michalswig.agencycrm.modules.crm.domain;

import com.michalswig.agencycrm.modules.identity.domain.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(
        name = "care_recipient",
        indexes = {
                @Index(name = "ix_care_recipient_client_id", columnList = "client_id")
        }
)
public class CareRecipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "first_name", nullable = false, length = 120)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 120)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "height_cm")
    private Integer heightCm;

    @Column(name = "weight_kg")
    private Integer weightKg;

    @Column(length = 20)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "mobility_level", nullable = false, length = 30)
    private MobilityLevel mobilityLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "dementia_level", nullable = false, length = 30)
    private DementiaLevel dementiaLevel;

    @Column(name = "has_ms", nullable = false)
    private boolean hasMs;

    @Column(name = "has_alzheimer", nullable = false)
    private boolean hasAlzheimer;

    @Column(name = "has_parkinson", nullable = false)
    private boolean hasParkinson;

    @Column(name = "diseases_notes", columnDefinition = "TEXT")
    private String diseasesNotes;

    @Column(name = "is_smoker", nullable = false)
    private boolean isSmoker;

    @Column(name = "has_pets", nullable = false)
    private boolean hasPets;

    @Column(name = "pets_notes", columnDefinition = "TEXT")
    private String petsNotes;

    @Column(name = "needs_transfer", nullable = false)
    private boolean needsTransfer;

    @Column(name = "transfer_type", length = 30)
    private String transferType;

    @Column(name = "lifting_aids_notes", columnDefinition = "TEXT")
    private String liftingAidsNotes;

    @Column(name = "has_catheter", nullable = false)
    private boolean hasCatheter;

    @Column(name = "has_stoma", nullable = false)
    private boolean hasStoma;

    @Column(name = "uses_diapers", nullable = false)
    private boolean usesDiapers;

    @Column(name = "medical_notes", columnDefinition = "TEXT")
    private String medicalNotes;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    protected CareRecipient() {}

    public CareRecipient(Client client, String firstName, String lastName,
                         LocalDate dateOfBirth, Integer heightCm, Integer weightKg, String gender,
                         MobilityLevel mobilityLevel, DementiaLevel dementiaLevel,
                         boolean hasMs, boolean hasAlzheimer, boolean hasParkinson, String diseasesNotes,
                         boolean isSmoker, boolean hasPets, String petsNotes,
                         boolean needsTransfer, String transferType, String liftingAidsNotes,
                         boolean hasCatheter, boolean hasStoma, boolean usesDiapers, String medicalNotes,
                         OffsetDateTime createdAt, User createdBy) {
        this.client = client;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.gender = gender;
        this.mobilityLevel = mobilityLevel;
        this.dementiaLevel = dementiaLevel;
        this.hasMs = hasMs;
        this.hasAlzheimer = hasAlzheimer;
        this.hasParkinson = hasParkinson;
        this.diseasesNotes = diseasesNotes;
        this.isSmoker = isSmoker;
        this.hasPets = hasPets;
        this.petsNotes = petsNotes;
        this.needsTransfer = needsTransfer;
        this.transferType = transferType;
        this.liftingAidsNotes = liftingAidsNotes;
        this.hasCatheter = hasCatheter;
        this.hasStoma = hasStoma;
        this.usesDiapers = usesDiapers;
        this.medicalNotes = medicalNotes;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() { return id; }
    public Client getClient() { return client; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}


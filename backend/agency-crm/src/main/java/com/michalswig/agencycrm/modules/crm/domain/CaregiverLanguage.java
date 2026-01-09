package com.michalswig.agencycrm.modules.crm.domain;

import com.michalswig.agencycrm.modules.identity.domain.User;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "caregiver_language",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_caregiver_language_caregiver_lang",
                        columnNames = {"caregiver_id", "language_code"}
                )
        },
        indexes = {
                @Index(name = "ix_caregiver_language_caregiver_id", columnList = "caregiver_id"),
                @Index(name = "ix_caregiver_language_language_code", columnList = "language_code")
        }
)
public class CaregiverLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    @Column(name = "language_code", nullable = false, length = 10)
    private String languageCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private LanguageLevel level;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    protected CaregiverLanguage() {}

    public CaregiverLanguage(Caregiver caregiver, String languageCode, LanguageLevel level,
                             OffsetDateTime createdAt, User createdBy) {
        this.caregiver = caregiver;
        this.languageCode = languageCode;
        this.level = level;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() { return id; }
    public Caregiver getCaregiver() { return caregiver; }
    public String getLanguageCode() { return languageCode; }
    public LanguageLevel getLevel() { return level; }
}


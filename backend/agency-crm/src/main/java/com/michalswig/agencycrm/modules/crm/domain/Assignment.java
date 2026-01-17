package com.michalswig.agencycrm.modules.crm.domain;

import com.michalswig.agencycrm.modules.identity.domain.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(
        name = "assignment",
        indexes = {
                @Index(name = "ix_assignment_client_id", columnList = "client_id"),
                @Index(name = "ix_assignment_care_recipient_id", columnList = "care_recipient_id"),
                @Index(name = "ix_assignment_owner_user_id", columnList = "owner_user_id"),
                @Index(name = "ix_assignment_status", columnList = "status"),
                @Index(name = "ix_assignment_start_date", columnList = "start_date")
        }
)
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "care_recipient_id", nullable = false)
    private CareRecipient careRecipient;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User owner;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "location_city", length = 120)
    private String locationCity;

    @Column(name = "salary_monthly")
    private Integer salaryMonthly;

    @Enumerated(EnumType.STRING)
    @Column(name = "language_level", nullable = false, length = 30)
    private LanguageLevel languageLevel;

    @Column(columnDefinition = "TEXT")
    private String requirements;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AssignmentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "close_reason", length = 40)
    private AssignmentCloseReason closeReason;

    @Column(name = "close_notes", columnDefinition = "TEXT")
    private String closeNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_caregiver_id")
    private Caregiver currentCaregiver;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    protected Assignment() {}

    public Assignment(Client client,
                      CareRecipient careRecipient,
                      User owner,
                      LocalDate startDate,
                      String locationCity,
                      Integer salaryMonthly,
                      LanguageLevel languageLevel,
                      String requirements,
                      AssignmentStatus status,
                      AssignmentCloseReason closeReason,
                      String closeNotes,
                      Caregiver currentCaregiver,
                      OffsetDateTime createdAt,
                      User createdBy) {
        this.client = client;
        this.careRecipient = careRecipient;
        this.owner = owner;
        this.startDate = startDate;
        this.locationCity = locationCity;
        this.salaryMonthly = salaryMonthly;
        this.languageLevel = languageLevel;
        this.requirements = requirements;
        this.status = status;
        this.closeReason = closeReason;
        this.closeNotes = closeNotes;
        this.currentCaregiver = currentCaregiver;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() { return id; }
    public Client getClient() { return client; }
    public CareRecipient getCareRecipient() { return careRecipient; }
    public User getOwner() { return owner; }
    public AssignmentStatus getStatus() { return status; }

    public void changeStatus(AssignmentStatus status) { this.status = status; }

    public void close(AssignmentCloseReason reason, String closeNotes) {
        this.status = AssignmentStatus.CLOSED;
        this.closeReason = reason;
        this.closeNotes = closeNotes;
    }

    public void setCurrentCaregiver(Caregiver currentCaregiver) {
        this.currentCaregiver = currentCaregiver;
    }

}


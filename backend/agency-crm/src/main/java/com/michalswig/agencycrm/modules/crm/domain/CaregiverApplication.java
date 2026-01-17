package com.michalswig.agencycrm.modules.crm.domain;

import com.michalswig.agencycrm.modules.identity.domain.User;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "caregiver_application",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_caregiver_application_assignment_caregiver",
                        columnNames = {"assignment_id", "caregiver_id"}
                )
        },
        indexes = {
                @Index(name = "ix_caregiver_application_assignment_id", columnList = "assignment_id"),
                @Index(name = "ix_caregiver_application_caregiver_id", columnList = "caregiver_id"),
                @Index(name = "ix_caregiver_application_status", columnList = "status")
        }
)
public class CaregiverApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ApplicationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "close_reason", length = 30)
    private ApplicationCloseReason closeReason;

    @Column(name = "match_score")
    private Integer matchScore;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    protected CaregiverApplication() {}

    public CaregiverApplication(Assignment assignment,
                                Caregiver caregiver,
                                ApplicationStatus status,
                                ApplicationCloseReason closeReason,
                                Integer matchScore,
                                String notes,
                                OffsetDateTime createdAt,
                                User createdBy) {
        this.assignment = assignment;
        this.caregiver = caregiver;
        this.status = status;
        this.closeReason = closeReason;
        this.matchScore = matchScore;
        this.notes = notes;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() { return id; }
    public Assignment getAssignment() { return assignment; }
    public Caregiver getCaregiver() { return caregiver; }
    public ApplicationStatus getStatus() { return status; }

    // --- domain methods for workflow ---
    public void changeStatus(ApplicationStatus newStatus) {
        this.status = newStatus;
    }

    public void close(ApplicationCloseReason reason) {
        this.status = ApplicationStatus.CLOSED;
        this.closeReason = reason;
    }


}

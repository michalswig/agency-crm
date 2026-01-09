package com.michalswig.agencycrm.modules.activity.domain;

import com.michalswig.agencycrm.modules.identity.domain.User;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "activity",
        indexes = {
                @Index(name = "ix_activity_target", columnList = "target_type,target_id"),
                @Index(name = "ix_activity_owner_user_id", columnList = "owner_user_id"),
                @Index(name = "ix_activity_due_at", columnList = "due_at"),
                @Index(name = "ix_activity_created_at", columnList = "created_at")
        }
)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", nullable = false, length = 30)
    private ActivityTargetType targetType;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ActivityType type;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "next_action", length = 255)
    private String nextAction;

    @Column(name = "due_at")
    private OffsetDateTime dueAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User ownerUser;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    protected Activity() {}

    public Activity(ActivityTargetType targetType, Long targetId, ActivityType type, String note,
                    String nextAction, OffsetDateTime dueAt, User ownerUser,
                    OffsetDateTime createdAt, User createdBy) {
        this.targetType = targetType;
        this.targetId = targetId;
        this.type = type;
        this.note = note;
        this.nextAction = nextAction;
        this.dueAt = dueAt;
        this.ownerUser = ownerUser;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() { return id; }
    public ActivityTargetType getTargetType() { return targetType; }
    public Long getTargetId() { return targetId; }
    public ActivityType getType() { return type; }
}


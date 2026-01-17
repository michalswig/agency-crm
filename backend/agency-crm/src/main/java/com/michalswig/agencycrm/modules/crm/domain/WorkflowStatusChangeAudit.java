package com.michalswig.agencycrm.modules.crm.domain;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "workflow_status_change_audit",
        indexes = {
                @Index(name = "ix_wf_audit_entity", columnList = "entity_type, entity_id"),
                @Index(name = "ix_wf_audit_changed_at", columnList = "changed_at")
        }
)
public class WorkflowStatusChangeAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false, length = 40)
    private WorkflowEntityType entityType;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "from_status", nullable = false, length = 40)
    private String fromStatus;

    @Column(name = "to_status", nullable = false, length = 40)
    private String toStatus;

    @Column(name = "actor_user_id", nullable = false)
    private Long actorUserId;

    @Column(name = "changed_at", nullable = false)
    private OffsetDateTime changedAt;

    @Column(name = "close_reason", length = 60)
    private String closeReason;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    protected WorkflowStatusChangeAudit() {}

    public WorkflowStatusChangeAudit(WorkflowEntityType entityType,
                                     Long entityId,
                                     String fromStatus,
                                     String toStatus,
                                     Long actorUserId,
                                     OffsetDateTime changedAt,
                                     String closeReason,
                                     String note) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.actorUserId = actorUserId;
        this.changedAt = changedAt;
        this.closeReason = closeReason;
        this.note = note;
    }
}

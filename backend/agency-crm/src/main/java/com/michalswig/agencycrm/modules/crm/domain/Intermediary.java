package com.michalswig.agencycrm.modules.crm.domain;

import com.michalswig.agencycrm.modules.identity.domain.User;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "intermediary",
        indexes = {
                @Index(name = "ix_intermediary_type", columnList = "type")
        }
)
public class Intermediary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private IntermediaryType type;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 50)
    private String phone;

    @Column(length = 200)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    protected Intermediary() {}

    public Intermediary(IntermediaryType type, String name, String phone, String email, String notes,
                        OffsetDateTime createdAt, User createdBy) {
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() { return id; }
    public IntermediaryType getType() { return type; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getNotes() { return notes; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public User getCreatedBy() { return createdBy; }
}

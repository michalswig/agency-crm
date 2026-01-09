package com.michalswig.agencycrm.modules.crm.domain;
import com.michalswig.agencycrm.modules.identity.domain.User;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "client",
        indexes = {
                @Index(name = "ix_client_intermediary_id", columnList = "intermediary_id"),
                @Index(name = "ix_client_email", columnList = "email")
        }
)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 120)
    private String city;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(length = 200)
    private String street;

    @Column(length = 50)
    private String phone;

    @Column(length = 200)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intermediary_id")
    private Intermediary intermediary;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    protected Client() {}

    public Client(String name, String country, String city, String postalCode, String street,
                  String phone, String email, String notes, Intermediary intermediary,
                  OffsetDateTime createdAt, User createdBy) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
        this.intermediary = intermediary;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }
    public String getStreet() { return street; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getNotes() { return notes; }
    public Intermediary getIntermediary() { return intermediary; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public User getCreatedBy() { return createdBy; }
}


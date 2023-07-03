package com.company.client.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Campaign  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;
    public String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="campaign_id",
            referencedColumnName = "id"
    )
    private List<Phone> phones;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="campaign_id",
            referencedColumnName = "id"
    )
    private List<Email> emails;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="campaign_id",
            referencedColumnName = "id"
    )
    private List<SentEmail> sentEmails;

}

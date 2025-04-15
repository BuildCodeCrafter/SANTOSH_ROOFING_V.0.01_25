package com.santosh.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
@EntityListeners(AuditingEntityListener.class)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String company;
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user; // owner

    @OneToMany(mappedBy = "customer")
    private List<Lead> leads;

    @OneToMany(mappedBy = "customer")
    private List<Deal> deals;

    @OneToMany(mappedBy = "customer")
    private List<Task> tasks;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}


package com.santosh.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userPermission;
    @ManyToOne
    @JsonBackReference
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "privilege_id")
    private Privilege privilege;
}


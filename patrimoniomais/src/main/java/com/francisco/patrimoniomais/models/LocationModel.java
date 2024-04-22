package com.francisco.patrimoniomais.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "tb_location")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class LocationModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "subsector_id")
    private SubsectorModel subsector;
}

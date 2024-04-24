package com.francisco.patrimoniomais.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "tb_subsector")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SubsectorModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "sector_id")
    private SectorModel sector;
    @Column(nullable = false)
    private Boolean active;

    public SubsectorModel(String name, SectorModel sector) {
        this.name = name;
        this.sector = sector;
        this.active = true;
    }
}

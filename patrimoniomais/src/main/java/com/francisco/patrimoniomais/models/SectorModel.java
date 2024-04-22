package com.francisco.patrimoniomais.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "tb_sector")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SectorModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyModel company;
}

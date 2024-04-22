package com.francisco.patrimoniomais.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "tb_company")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompanyModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cnpj;
    @Column(nullable = false)
    private String description;
}

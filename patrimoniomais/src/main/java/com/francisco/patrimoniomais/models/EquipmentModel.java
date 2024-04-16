package com.francisco.patrimoniomais.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_equipment")
@Getter
@Setter
@NoArgsConstructor
public class EquipmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String imagePath;

    public EquipmentModel(String code, String name, String description, String imagePath) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }
}

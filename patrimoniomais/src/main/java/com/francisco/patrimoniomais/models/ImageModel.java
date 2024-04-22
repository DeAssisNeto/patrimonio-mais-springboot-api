package com.francisco.patrimoniomais.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "tb_image")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ImageModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String path;
    @ManyToOne
    @JoinColumn(name = "patrimony_id")
    private PatrimonyModel patrimony;
}

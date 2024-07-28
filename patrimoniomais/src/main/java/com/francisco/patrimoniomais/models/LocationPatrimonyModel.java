package com.francisco.patrimoniomais.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "tb_user_company")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class LocationPatrimonyModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationModel location;
    @ManyToOne
    @JoinColumn(name = "patrimony_id")
    private PatrimonyModel patrimony;
    @Column(nullable = false)
    private Boolean active;

    public LocationPatrimonyModel(LocationModel location, PatrimonyModel patrimony) {
        this.location = location;
        this.patrimony = patrimony;
        this.active = true;
    }
}


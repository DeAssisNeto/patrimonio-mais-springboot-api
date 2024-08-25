package com.francisco.patrimoniomais.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tb_location_patrimony")
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
    private LocalDateTime createAt;
    @Column(nullable = false)
    private Boolean active;

    public LocationPatrimonyModel(LocationModel location, PatrimonyModel patrimony, LocalDateTime createAt) {
        this.location = location;
        this.patrimony = patrimony;
        this.createAt = createAt;
        this.active = true;
    }
}


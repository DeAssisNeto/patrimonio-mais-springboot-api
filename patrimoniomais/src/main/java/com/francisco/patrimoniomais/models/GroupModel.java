package com.francisco.patrimoniomais.models;


import com.francisco.patrimoniomais.enums.TypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_group")
@Getter
@Setter
@NoArgsConstructor
public class GroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private TypeEnum type;
    @Column(nullable = false)
    private String subType;
    @ManyToOne
    @JoinColumn(
            name = "equipment_id"
    )
    private EquipmentModel equipmentModel;

}

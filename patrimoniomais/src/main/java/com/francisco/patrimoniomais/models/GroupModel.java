package com.francisco.patrimoniomais.models;


import com.francisco.patrimoniomais.enums.GroupEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "tb_group")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class GroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private GroupEnum name;
    @Column()
    private Boolean active;

    public GroupModel(GroupEnum name) {
        this.name = name;
        this.active = true;
    }
}

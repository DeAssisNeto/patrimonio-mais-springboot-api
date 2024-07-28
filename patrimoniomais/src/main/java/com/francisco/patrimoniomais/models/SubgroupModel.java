package com.francisco.patrimoniomais.models;

import com.francisco.patrimoniomais.enums.GroupEnum;
import com.francisco.patrimoniomais.enums.SubgroupEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "tb_subgroup")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SubgroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private SubgroupEnum name;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupModel group;

    public SubgroupModel(SubgroupEnum name, GroupModel group) {
        this.name = name;
        this.group = group;
    }
}

package com.francisco.patrimoniomais.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tb_patrimony")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PatrimonyModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(unique = true, length = 20)
    private String serialNumber;
    @Column(nullable = false)
    private LocalDateTime acquisitionDate;
    @Column(nullable = false)
    private BigDecimal acquisitionValue;
    @ManyToOne
    @JoinColumn(name = "subgroup_id")
    private SubgroupModel subgroup;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userAt;
    @ManyToOne
    @JoinColumn(name = "tombamento_id")
    private TombamentoModel tombamento;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyModel company;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean active;

    public PatrimonyModel(String name, String description, String serialNumber,
                          LocalDateTime acquisitionDate, BigDecimal acquisitionValue,
                          SubgroupModel subgroupModel, UserModel userAt, TombamentoModel tombamento,
                          CompanyModel company) {
        this.name = name;
        this.description = description;
        this.serialNumber = serialNumber;
        this.acquisitionDate = acquisitionDate;
        this.acquisitionValue = acquisitionValue;
        this.subgroup = subgroupModel;
        this.userAt = userAt;
        this.tombamento = tombamento;
        this.company = company;
        this.active = true;
    }
}

package com.francisco.patrimoniomais.models;

import com.francisco.patrimoniomais.enums.GroupEnum;
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
    @Column(nullable = false)
    private GroupEnum groupType;
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



}

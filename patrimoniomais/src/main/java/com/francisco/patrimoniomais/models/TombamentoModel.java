package com.francisco.patrimoniomais.models;

import com.francisco.patrimoniomais.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tb_tombamento")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TombamentoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private StatusEnum statusType;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private Boolean active;

    public TombamentoModel(StatusEnum statusType, String code, LocalDateTime createAt) {
        this.statusType = statusType;
        this.code = code;
        this.createAt = createAt;
        this.active = true;
    }
}

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
public class UserCompanyModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyModel company;
    @Column(nullable = false)
    private Boolean active;

    public UserCompanyModel(UserModel user, CompanyModel company) {
        this.user = user;
        this.company = company;
        this.active = true;
    }
}

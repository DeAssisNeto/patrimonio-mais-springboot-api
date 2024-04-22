package com.francisco.patrimoniomais.dtos;

import com.francisco.patrimoniomais.models.CompanyModel;

public record CompanyPutDto(String name, String cnpj, String description) {

        public void toModel(CompanyModel model){
                if (this.name != null){
                        model.setName(name);
                }
                if (this.cnpj != null){
                        model.setCnpj(cnpj);
                }
                if (this.description != null){
                        model.setDescription(description);
                }
        }

}

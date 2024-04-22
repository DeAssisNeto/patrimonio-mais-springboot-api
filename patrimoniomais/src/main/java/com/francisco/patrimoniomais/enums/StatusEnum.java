package com.francisco.patrimoniomais.enums;

public enum StatusEnum {
    NOVO("novo"),
    USADO("usado"),
    DANIFICADO("danificado"),
    REMOVIDO("removido");

    private String status;

    private StatusEnum(String status){
        this.status = status;
    }
}

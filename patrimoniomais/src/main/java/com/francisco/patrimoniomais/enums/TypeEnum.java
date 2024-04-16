package com.francisco.patrimoniomais.enums;

public enum TypeEnum {
    ELETRONICO("eletronico");

    private final String type;

    TypeEnum(String type){
        this.type = type;
    }

    public String getType(){return this.type;}
}

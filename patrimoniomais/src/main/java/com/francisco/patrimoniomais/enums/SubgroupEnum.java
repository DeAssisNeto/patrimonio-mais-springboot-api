package com.francisco.patrimoniomais.enums;

public enum SubgroupEnum {
    DESKTOPS("desktops"),
    NOTEBOOKS("notebooks")
    ;

    private String subgroupType;

    SubgroupEnum(String subgroupType){
        this.subgroupType = subgroupType;
    }
}

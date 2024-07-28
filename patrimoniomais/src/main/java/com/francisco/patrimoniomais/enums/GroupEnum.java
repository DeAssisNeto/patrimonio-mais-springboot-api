package com.francisco.patrimoniomais.enums;

public enum GroupEnum {
    ELETRONICOS("eletronicos"),
    MOVEIS("moveis")
    ;

    private String groupType;

    GroupEnum(String groupType){
        this.groupType = groupType;
    }
}

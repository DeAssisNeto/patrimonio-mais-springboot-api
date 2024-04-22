package com.francisco.patrimoniomais.dtos;

import com.francisco.patrimoniomais.models.PatrimonyModel;

public record PatrimonyPutRecordDto(String name, String description, String imagePath) {

//    public void toEquipment(PatrimonyModel patrimonyModel){
//        if (name != null) patrimonyModel.setName(name);
//        if (description != null) patrimonyModel.setDescription(description);
//        if (imagePath != null) patrimonyModel.setImagePath(imagePath);
//    }

}

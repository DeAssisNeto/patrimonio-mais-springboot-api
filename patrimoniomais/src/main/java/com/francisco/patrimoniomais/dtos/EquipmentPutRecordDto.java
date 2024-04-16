package com.francisco.patrimoniomais.dtos;

import com.francisco.patrimoniomais.models.EquipmentModel;

public record EquipmentPutRecordDto (String name, String description, String imagePath) {

    public void toEquipment(EquipmentModel equipmentModel){
        if (name != null) equipmentModel.setName(name);
        if (description != null) equipmentModel.setDescription(description);
        if (imagePath != null) equipmentModel.setImagePath(imagePath);
    }

}

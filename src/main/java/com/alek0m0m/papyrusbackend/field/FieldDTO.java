package com.alek0m0m.papyrusbackend.field;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.alek0m0m.papyrusbackend.resource.Resource;
import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO extends BaseEntityDTO<Field> {

    private String name;
    private List<ResourceDTO> resources = new ArrayList<>();


    // ----------------- Constructors -----------------

    public FieldDTO(Field input) {
        this.setId(input.getId() != 0 ? input.getId() : 0L);
        this.name = input.getName();
        this.resources = input.getResources().stream()
                .map(ResourceDTO::new).toList();
    }
    public FieldDTO(FieldDTOInput input) {
        this.setId(input.getId() != null ? input.getId() : 0L);
        this.name = input.getName();
        this.resources = input.getResources().stream()
                .map(ResourceDTO::new).toList();
    }

    // ----------------- Mapper logic  -----------------
    @Override
    public Field toEntity() {
        return new Field()
                .setId(this.getId())
                .setName(this.getName())
                .setResources(this.getResources().stream().map(ResourceDTO::toEntity).toList());
    }


    // ----------------- Setters -----------------
    public FieldDTO setId(Long id) {
        super.setId(id);
        return this;
    }

    public FieldDTO setName(String name) {
        this.name = name;
        return this;
    }

    public FieldDTO setResources(List<ResourceDTO> resources) {
        this.resources = resources;
        return this;
    }


}

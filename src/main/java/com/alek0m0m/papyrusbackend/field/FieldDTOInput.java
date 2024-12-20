package com.alek0m0m.papyrusbackend.field;

import com.alek0m0m.papyrusbackend.resource.Resource;
import com.alek0m0m.papyrusbackend.resource.ResourceDTOInput;
import com.alek0m0m.papyrusbackend.user.UserDTOInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString

@AllArgsConstructor
public class FieldDTOInput {

    private Long id;
    private String name;

    private List<ResourceDTOInput> resources;


    // ------------------ Constructors ------------------

    public FieldDTOInput () {
        this.resources = new ArrayList<>();
    }

    public FieldDTOInput(FieldDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();

        if (dto.getResources() != null) {
            this.resources = dto.getResources().stream()
                    .map(ResourceDTOInput::new).toList();
        }
    }


    // ------------------ Setters ------------------
    public FieldDTOInput setId(long id) {
        this.id = id;
        return this;
    }

    public FieldDTOInput setName (String name) {
        this.name = name;
        return this;
    }

    public FieldDTOInput setResources(List<ResourceDTOInput> resources) {
        this.resources = resources;
        return this;
    }

}

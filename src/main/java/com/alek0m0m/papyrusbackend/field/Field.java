package com.alek0m0m.papyrusbackend.field;

import com.Alek0m0m.library.jpa.BaseEntity;
import com.alek0m0m.papyrusbackend.resource.Resource;
import com.alek0m0m.papyrusbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Field extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Resource> resources = new ArrayList<>();


    public Field setId(long id) {
        this.id = id;
        return this;
    }

    public Field setName(String name) {
        this.name = name;
        return this;
    }

    public Field setResources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }

}
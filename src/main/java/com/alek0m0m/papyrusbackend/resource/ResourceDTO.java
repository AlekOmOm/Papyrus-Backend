package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.alek0m0m.papyrusbackend.user.UserDTO;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDTO extends BaseEntityDTO<Resource> {

    private String name;
    private String author;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String refId;


    // ----------------- Constructors -----------------

    public ResourceDTO(Resource input) {
        if (input == null) { return; }
        setId(input.getId());
        this.name = input.getName();
        this.author = input.getAuthor();
        this.fromDate = input.getFromDate();
        this.toDate = input.getToDate();
        this.refId = input.getRefId();
    }

    public ResourceDTO(ResourceDTOInput input) {
        if (input == null) { return; }
        setId(input.getId());
        this.name = input.getName();
        this.author = input.getAuthor();
        this.fromDate = input.getFromDate();
        this.toDate = input.getToDate();
        this.refId = input.getRefId();
    }



    // ----------------- Mapper Logic -----------------
    @Override
    public Resource toEntity() {
        Resource res = new Resource()
                .setId(this.getId() != null ? this.getId() : 0L)
                .setName(this.getName())
                .setAuthor(this.getAuthor())
                .setFromDate(this.getFromDate())
                .setToDate(this.getToDate())
                .setRefId(this.getRefId());
        return res;
    }



    // ----------------- Setters -----------------
    public ResourceDTO setId(Long id) {
        super.setId(id);
        return this;
    }

    public ResourceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ResourceDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public ResourceDTO setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public ResourceDTO setToDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    public ResourceDTO setRef_id(String refId) {
        this.refId = refId;
        return this;
    }


}

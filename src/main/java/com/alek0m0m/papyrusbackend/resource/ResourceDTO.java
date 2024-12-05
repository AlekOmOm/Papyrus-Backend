package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserDTO;
import jakarta.persistence.Version;
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
    private List<UserDTO> users = new ArrayList<>();

    @Version
    private int version;

    // ----------------- Constructors -----------------

    public ResourceDTO(Resource entity) {
        setId(entity.getId());
        this.name = entity.getName();
        this.author = entity.getAuthor();
        this.fromDate = entity.getFromDate();
        this.toDate = entity.getToDate();
        this.users = entity.getUsers().stream()
                .map(UserDTO::new).toList();
        this.version = entity.getVersion();
    }

    public ResourceDTO(ResourceDTOInput input) {
        setId(input.getId());
        this.name = input.getName();
        this.author = input.getAuthor();
        this.fromDate = input.getFromDate();
        this.toDate = input.getToDate();
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
                .setUsers(this.getUsers().stream()
                        .map(UserDTO::toEntity).toList());
        res.setVersion(this.version);
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

    public ResourceDTO setUsers(List<UserDTO> users) {
        this.users = users;
        return this;
    }

}

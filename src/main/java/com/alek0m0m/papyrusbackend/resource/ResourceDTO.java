package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.alek0m0m.papyrusbackend.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDTO extends BaseEntityDTO<Resource> {

    private String name;
    private String author;
    private LocalDate fromDate;
    private LocalDate toDate;
    private List<User> users = new ArrayList<>();


    @Override
    public Resource toEntity() {
        return new Resource()
                .setId(this.getId())
                .setName(this.getName())
                .setAuthor(this.getAuthor())
                .setFromDate(this.getFromDate())
                .setToDate(this.getToDate())
                .setUsers(this.getUsers());
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

    public ResourceDTO setUsers(List<User> users) {
        this.users = users;
        return this;
    }

}

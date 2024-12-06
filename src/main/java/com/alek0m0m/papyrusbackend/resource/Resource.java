package com.alek0m0m.papyrusbackend.resource;
import com.Alek0m0m.library.jpa.BaseEntity;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Resource extends BaseEntity {

    private String name;
    private String author;
    private LocalDate fromDate;
    private LocalDate toDate;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "savedResources")
    private List<User> users;

    @ManyToOne
    private Field field;

    // ------------------ Setters ------------------
    public Resource setId(long id) {
        this.id = id;
        return this;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public Resource setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Resource setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public Resource setToDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    public Resource setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Resource setField(Field field) {
        this.field = field;
        return this;
    }


    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", field=" + field +
                '}';
    }

}
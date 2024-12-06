package com.alek0m0m.papyrusbackend.resource;

import com.alek0m0m.papyrusbackend.user.UserDTOInput;
import com.alek0m0m.papyrusbackend.user.UserMapper;
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
public class ResourceDTOInput {

    private Long id;
    private String name;
    private String author;
    private LocalDate fromDate;
    private LocalDate toDate;
    private List<UserDTOInput> users = new ArrayList<>();

    public ResourceDTOInput(ResourceDTO input) {
        this.id = input.getId();
        this.name = input.getName();
        this.author = input.getAuthor();
        this.fromDate = input.getFromDate();
        this.toDate = input.getToDate();
        if (input.getUsers() != null) {
            this.users = input.getUsers().stream()
                    .map(UserDTOInput::new).toList();
        }
    }

    // setters return ResourceDTOInput

    public ResourceDTOInput setId(Long id) {
        this.id = id;
        return this;
    }

    public ResourceDTOInput setName (String name) {
        this.name = name;
        return this;
    }

    public ResourceDTOInput setAuthor(String author) {
        this.author = author;
        return this;
    }

    public ResourceDTOInput setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public ResourceDTOInput setToDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    public ResourceDTOInput setUsers(List<UserDTOInput> users) {
        this.users = users;
        return this;
    }


}

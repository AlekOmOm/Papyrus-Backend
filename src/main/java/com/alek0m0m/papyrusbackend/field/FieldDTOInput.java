package com.alek0m0m.papyrusbackend.field;

import com.alek0m0m.papyrusbackend.user.UserDTOInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTOInput {

    private Long id;
    private String name;
    private UserDTOInput user;

    // setters return ResourceDTOInput

    public FieldDTOInput setId(long id) {
        this.id = id;
        return this;
    }

    public FieldDTOInput setName (String name) {
        this.name = name;
        return this;
    }

    public FieldDTOInput setUser(UserDTOInput user) {
        this.user = user;
        return this;
    }

}

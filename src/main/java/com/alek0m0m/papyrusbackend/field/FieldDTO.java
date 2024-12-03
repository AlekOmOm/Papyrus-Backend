package com.alek0m0m.papyrusbackend.field;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO extends BaseEntityDTO<Field> {

    private String name;
    private UserDTO user;


    @Override
    public Field toEntity() {
        return new Field()
                .setId(this.getId())
                .setName(this.getName())
                .setUser(this.getUser().toEntity());
    }

    public FieldDTO setId(long id) {
        super.setId(id);
        return this;
    }

    public FieldDTO setName(String name) {
        this.name = name;
        return this;
    }

    public FieldDTO setUser(UserDTO user) {
        this.user = user;
        return this;
    }


}

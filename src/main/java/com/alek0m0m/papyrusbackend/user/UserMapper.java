package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.*;

import com.alek0m0m.papyrusbackend.field.FieldDTO;
import com.alek0m0m.papyrusbackend.field.FieldMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.Arrays.stream;

@Service
public class UserMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {
    private final FieldMapper fieldMapper;

    public UserMapper(FieldMapper fieldMapper) {
        this.fieldMapper = fieldMapper;
    }

    @Override
    public UserDTO map(UserDTOInput dtoInput, User entity) {
        UserDTO dto = new UserDTO();

        if (entity == null) { // -> DTOInput to DTO
            dto.setId(dtoInput.getId() != 0L ? dtoInput.getId() : 0L)
                    .setName(dtoInput.getName())
                    .setEmail(dtoInput.getEmail())
                    .setPassword(dtoInput.getPassword())
                    .setRole(dtoInput.getRole())
                    .setField(fieldMapper.convert(dtoInput.getField())); // not nullable, since User init with root field

        } else { // -> Entity to DTO
            dto.setId(entity.getId() != 0L ? entity.getId() : dtoInput.getId())
                    .setName(entity.getName())
                    .setEmail(entity.getEmail())
                    .setPassword(entity.getPassword())
                    .setRole(entity.getRole());
        }
        return dto;
    }


    private void debugPrint(String message, List<Object> list) {
        System.out.println(message);
        for (Object item : list) {
            if (item != null) {
                System.out.println(item);
            } else {
                System.out.println("null");
            }
        }

    }
}


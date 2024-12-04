package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.EntityToDTOMapperImpl;
import com.alek0m0m.papyrusbackend.field.FieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {
    private final FieldMapper fieldMapper;

    @Autowired
    public UserMapper(FieldMapper fieldMapper) {
        this.fieldMapper = fieldMapper;
    }

    @Override
    public UserDTO map(UserDTOInput dtoInput, User entity) {
        UserDTO dto = new UserDTO();

        if (entity == null) { // -> DTOInput to DTO
            dto.setId(dtoInput.getId() != null ? dtoInput.getId() : 0L)
                    .setName(dtoInput.getName())
                    .setEmail(dtoInput.getEmail())
                    .setPassword(dtoInput.getPassword())
                    .setRole(dtoInput.getRole())
                    .setField(fieldMapper.convert(dtoInput.getField())); // not nullable, since User init with root field

        } else { // -> Entity to DTO
            dto.setId(entity.getId() != 0 ? entity.getId() : (dtoInput.getId() != null ? dtoInput.getId() : 0L))
                    .setName(entity.getName())
                    .setEmail(entity.getEmail())
                    .setPassword(entity.getPassword())
                    .setRole(entity.getRole())
                    .setField(fieldMapper.convert(entity.getField()));
        }
        return dto;
    }
}
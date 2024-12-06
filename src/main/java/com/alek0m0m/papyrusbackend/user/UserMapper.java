package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.EntityToDTOMapperImpl;
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.field.FieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class UserMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {

    @Override
    public UserDTO toDTO(UserDTOInput dtoInput) {
        return new UserDTO(dtoInput);
    }

    @Override
    public UserDTO map(UserDTOInput dtoInput, User entity) {
        return map(toDTO(dtoInput), entity);
    }

    @Override
    public UserDTO map(UserDTO dto, User entity) {
        if (entity == null && dto == null) {
            return null;
        }

        if (entity == null) {
            return dto;
        }

        if (dto == null) {
            return new UserDTO(entity);
        }

        return new UserDTO(entity)
                .setName(dto.getName() != null ? dto.getName() : entity.getName())
                .setEmail(dto.getEmail() != null ? dto.getEmail() : entity.getEmail())
                .setPassword(dto.getPassword() != null ? dto.getPassword() : entity.getPassword())
                .setRole(dto.getRole() != null ? dto.getRole() : entity.getRole())
                .setField(dto.getField() != null
                        ? dto.getField()
                        : new FieldMapper().mapEntityToDTO(entity.getField()));
    }



    // ----------------- helper -----------------

    private void printCount(String message, String type, Object... args) {

        System.out.println(message + " " + type + " ");
        for (Object arg : args) {
            if (arg == null) {
                continue;
            }
            System.out.println(" "+arg);
        }

    }
}
package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.*;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class UserMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {

    private final BiFunction<UserDTOInput, User, UserDTO> toDTO = (dtoInput, entity) -> {
        UserDTO dto = new UserDTO();
        if (entity == null) {
            dto
                    .setId(dtoInput.getId())
                    .setName(dtoInput.getName())
                    .setEmail(dtoInput.getEmail())
                    .setPassword(dtoInput.getPassword())
                    .setRole(dtoInput.getRole());
        } else {
            entity
                    .setId(dtoInput.getId())
                    .setName(dtoInput.getName())
                    .setEmail(dtoInput.getEmail())
                    .setPassword(dtoInput.getPassword())
                    .setRole(dtoInput.getRole());
        }
        return dto;
    };

    @Override
    public BiFunction<UserDTOInput, User, UserDTO> toDTO(UserDTOInput dtoInput, User entity) {
        return toDTO;
    }
}
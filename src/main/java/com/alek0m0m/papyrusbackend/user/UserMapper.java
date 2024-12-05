package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.*;

import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UserMapper extends EntityToDTOMapperImpl<UserDTOInput, UserDTO, User> {
    @Override
    public UserDTO map(UserDTOInput dtoInput, User entity) {
        UserDTO dto = new UserDTO();

        if (entity == null) {
            dto.setId(dtoInput.getId() != 0L ? dtoInput.getId() : 0L)
                    .setName(dtoInput.getName())
                    .setEmail(dtoInput.getEmail())
                    .setPassword(dtoInput.getPassword())
                    .setRole(dtoInput.getRole());
        } else {
            dto.setId(entity.getId() != 0L ? entity.getId() : dtoInput.getId())
                    .setName(entity.getName())
                    .setEmail(entity.getEmail())
                    .setPassword(entity.getPassword())
                    .setRole(entity.getRole())
                    //This code was added later in the mapper - can be moved if creating problems.
                    .setResources(entity.getResources().stream()
                            .map(resource -> {
                                ResourceDTO resourceDTO = new ResourceDTO();
                                resourceDTO.setId(resource.getId());
                                resourceDTO.setName(resource.getName());
                                resourceDTO.setAuthor(resource.geAuthor());
                                return resourceDTO;
                            })
                            .collect(Collectors.toList()));
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


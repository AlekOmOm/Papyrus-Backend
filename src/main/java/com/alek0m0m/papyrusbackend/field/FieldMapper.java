package com.alek0m0m.papyrusbackend.field;

import com.Alek0m0m.library.jpa.EntityToDTOMapperImpl;
import com.alek0m0m.papyrusbackend.resource.Resource;
import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import com.alek0m0m.papyrusbackend.resource.ResourceDTOInput;
import com.alek0m0m.papyrusbackend.resource.ResourceMapper;
import com.alek0m0m.papyrusbackend.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FieldMapper extends EntityToDTOMapperImpl<FieldDTOInput, FieldDTO, Field> {

    @Override
    public FieldDTO toDTO(FieldDTOInput dtoInput) {
        return new FieldDTO(dtoInput);
    }

    @Override
    public FieldDTO map(FieldDTOInput dtoInput, Field entity) {
        return map(toDTO(dtoInput), entity);
    }

    @Override
    public FieldDTO map(FieldDTO dto, Field entity) {
        if (entity == null && dto == null) {
            return null;
        }

        if (entity == null) {
            return dto;
        }

        if (dto == null) {
            return new FieldDTO(entity);
        }

        ResourceMapper resourceMapper = new ResourceMapper();

        return new FieldDTO(entity)
                .setName(
                        Optional.ofNullable(dto.getName())
                                .orElse(entity.getName()))
                .setResources(
                        Optional.ofNullable(dto.getResources())
                                .orElse(entity.getResources().stream()
                                        .map(resourceMapper::mapEntityToDTO)
                                        .toList())
                );
    }
}
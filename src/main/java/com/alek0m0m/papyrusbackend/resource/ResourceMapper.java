package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.jpa.EntityToDTOMapperImpl;
import com.alek0m0m.papyrusbackend.field.FieldMapper;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserDTO;
import com.alek0m0m.papyrusbackend.user.UserDTOInput;
import org.springframework.stereotype.Service;

@Service
public class ResourceMapper extends EntityToDTOMapperImpl<ResourceDTOInput, ResourceDTO, Resource> {

    @Override
    public ResourceDTO toDTO(ResourceDTOInput dtoInput) {
        return new ResourceDTO(dtoInput);
    }

    @Override
    public ResourceDTO map(ResourceDTOInput dtoInput, Resource entity) {
        return map(toDTO(dtoInput), entity);
    }

    @Override
    public ResourceDTO map(ResourceDTO dto, Resource entity) {
        if (entity == null && dto == null) {
            return null;
        }

        if (entity == null) {
            return dto;
        }

        if (dto == null) {
            return new ResourceDTO(entity);
        }

        return new ResourceDTO(entity)
                    .setName(dto.getName() != null ? dto.getName() : entity.getName())
                    .setAuthor(dto.getAuthor() != null ? dto.getAuthor() : entity.getAuthor())
                    .setFromDate(dto.getFromDate() != null ? dto.getFromDate() : entity.getFromDate())
                    .setToDate(dto.getToDate() != null ? dto.getToDate() : entity.getToDate())
                    .setRef_id(dto.getRefId() != null ? dto.getRefId() : entity.getRefId());
    }


}
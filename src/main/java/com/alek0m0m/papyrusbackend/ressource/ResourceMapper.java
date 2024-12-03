package com.alek0m0m.papyrusbackend.ressource;

import com.Alek0m0m.library.jpa.EntityToDTOMapperImpl;
import org.springframework.stereotype.Service;

@Service
public class ResourceMapper extends EntityToDTOMapperImpl<ResourceDTOInput, ResourceDTO, Resource> {


    @Override
    public ResourceDTO map(ResourceDTOInput dtoInput, Resource entity) {
        ResourceDTO dto = new ResourceDTO();

        if (entity == null) {
            dto.setId(dtoInput.getId() != 0L ? dtoInput.getId() : 0L)
                    .setName(dtoInput.getName())
                    .setAuthor(dtoInput.getAuthor())
                    .setFromDate(dtoInput.getFromDate())
                    .setToDate(dtoInput.getToDate());
        } else {
            dto.setId(entity.getId() != 0L ? entity.getId() : dtoInput.getId())
                    .setName(entity.getName())
                    .setAuthor(entity.getAuthor())
                    .setFromDate(entity.getFromDate())
                    .setToDate(entity.getToDate());
        }
        return dto;
    }
}

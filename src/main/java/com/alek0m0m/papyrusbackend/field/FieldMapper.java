package com.alek0m0m.papyrusbackend.field;

import com.Alek0m0m.library.jpa.EntityToDTOMapperImpl;
import com.alek0m0m.papyrusbackend.resource.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldMapper extends EntityToDTOMapperImpl<FieldDTOInput, FieldDTO, Field> {

    private final ResourceMapper resourceMapper;

    @Autowired
    public FieldMapper(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Override
    public FieldDTO map(FieldDTOInput dtoInput, Field entity) {
        FieldDTO dto = new FieldDTO();

        if (entity == null) {
            dto.setId(dtoInput.getId() != null ? dtoInput.getId() : 0L)
                    .setName(dtoInput.getName())
                    .setResources(resourceMapper.mapInputsToDTOs(
                            dtoInput.getResources())
                                .stream().toList());

        } else {
            dto.setId(entity.getId() != 0 ? entity.getId() : (dtoInput.getId() != null ? dtoInput.getId() : 0L))
                    .setName(entity.getName())
                    .setResources(entity.getResources().stream()
                            .map(resourceMapper::convert)
                            .toList());
        }
        return dto;
    }
}
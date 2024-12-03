package com.alek0m0m.papyrusbackend.field;

import com.Alek0m0m.library.jpa.EntityToDTOMapperImpl;
import com.alek0m0m.papyrusbackend.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldMapper extends EntityToDTOMapperImpl<FieldDTOInput, FieldDTO, Field> {

    private final UserMapper userMapper;

    @Autowired
    public FieldMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public FieldDTO map(FieldDTOInput dtoInput, Field entity) {
        FieldDTO dto = new FieldDTO();

        if (entity == null) {
            dto.setId(dtoInput.getId() != 0L ? dtoInput.getId() : 0L)
                    .setName(dtoInput.getName())
                    .setUser(userMapper.convert(dtoInput.getUser()));
        } else {
            dto.setId(entity.getId() != 0L ? entity.getId() : dtoInput.getId())
                    .setName(entity.getName())
                    .setUser(userMapper.convert(entity.getUser()));
        }
        return dto;
    }
}

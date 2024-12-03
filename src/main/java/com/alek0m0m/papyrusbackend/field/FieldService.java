package com.alek0m0m.papyrusbackend.field;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FieldService extends BaseService<FieldDTOInput, FieldDTO, Field, FieldMapper, FieldRepository> {

    private final FieldRepository repository;

    @Autowired
    public FieldService(FieldRepository repository, FieldMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
    }

    @Override
    protected void resetIncrement() {
        System.out.println("field resetIncrement");
    }


    @Transactional
    public FieldDTO save(FieldDTO input) {

        // filter added to prevent duplicate users
        List<FieldDTO> existingResources = findBy(input.getName(), input.getUser().getName());
        if (!existingResources.isEmpty()) {
            return existingResources.get(0);
        }

        // if no duplicate users, save the user
        return super.save(input);
    }


    public List<FieldDTO> findBy(String name, String userName) {
        List<FieldDTO> repoEntities = getDtoMapper().mapToDTOs(getRepository().findAll()).stream().toList();

        return repoEntities.stream()
                .filter(dto ->
                        dto.getName().equals(name)
                                &&
                                dto.getUser().getName().equals(userName))
                .toList();
    }

}
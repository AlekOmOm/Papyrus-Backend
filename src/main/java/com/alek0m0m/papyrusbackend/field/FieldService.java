package com.alek0m0m.papyrusbackend.field;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import com.alek0m0m.papyrusbackend.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FieldService extends BaseService<FieldDTOInput, FieldDTO, Field, FieldMapper, FieldRepository> {

    private final FieldRepository repository;
    private final ResourceService resourceService;

    @Autowired
    public FieldService(FieldRepository repository, FieldMapper mapper, ResourceService resourceService) {
        super(repository, mapper);
        this.repository = repository;
        this.resourceService = resourceService;
    }

    @Override
    protected void resetIncrement() {
        System.out.println("field resetIncrement");
    }

    @Transactional
    public FieldDTO save(FieldDTO input) {
        if (input == null) {
            return null;
        }

        FieldDTO foundField = find(input);
        if (foundField != null) {
            // Copy the necessary fields from input to foundField
            foundField.setName(input.getName()); // TODO: set attributes
                saveAndSetResources(input, foundField);

            return super.save(foundField);
        }

        saveAndSetResources(input, input);
        // input.setResources(input.getResources());
        return super.save(input);
    }

    private void saveAndSetResources(FieldDTO input, FieldDTO foundField) {
        System.out.println("saveAndSetResources");
        foundField.setResources(input.getResources().stream()
                .map(resourceDto -> {
                    System.out.println("savedResource: " + resourceDto);
                    ResourceDTO savedResource = resourceDto;
                    System.out.println("savedResource: " + savedResource);
                    System.out.println();
                    return savedResource;
                })
                .collect(Collectors.toList()));
    }


    @Transactional
    public FieldDTO find(FieldDTO field) {

        if (field == null) {
            return null;
        }

        if (field.getId() != 0) {
            return findByIdWithResources(field.getId());
        }

        if (field.getName() != null) {
            return findBy(field.getName()).stream()
                    .filter(Objects::isNull)
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    public List<FieldDTO> findBy(String fieldName) {
        List<FieldDTO> repoEntities = getDtoMapper().mapToDTOs(getRepository()
                .findAll()).stream().toList();

        repoEntities.stream().map(FieldDTO::getResources).forEach(List::size);

        return repoEntities.stream()
                .filter(dto ->
                        dto.getName().equals(fieldName))
                .toList();
    }


    @Transactional
    public FieldDTO findByIdWithResources(Long id) {
        FieldDTO field = findById(id);
        field.getResources().size(); // Initialize the collection
        return field;
    }
}
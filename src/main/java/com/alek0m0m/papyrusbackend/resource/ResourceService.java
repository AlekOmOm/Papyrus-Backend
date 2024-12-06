package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.field.FieldDTO;
import com.alek0m0m.papyrusbackend.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService extends BaseService<ResourceDTOInput, ResourceDTO, Resource, ResourceMapper, ResourceRepository> {

    private final ResourceRepository repository;
    private final ResourceMapper mapper;

    @Autowired
    public ResourceService(ResourceRepository repository, ResourceMapper mapper, ResourceRepository resourceRepository) {
        super(repository, mapper);
        this.repository = resourceRepository;
        this.mapper = mapper;
    }

    @Override
    protected void resetIncrement() {
        // ...
    }


    @Transactional
    public ResourceDTO save(ResourceDTO input) {
        if (input == null) {
            return null;
        }

        ResourceDTO existing = find(input);

        if (existing != null) {
            existing = mapper.map(input, existing.toEntity());
            return super.update(existing);
        }

        return super.save(input);
    }



    // ----------------- CRUD -----------------

    public ResourceDTO find(ResourceDTO dto) {
        if (dto.getId() != null && dto.getId() != 0) {
            System.out.println("userDTO.getId() != null: "+dto.getId());
            return findById(dto.getId());
        }

        // name and author != null
        if (dto.getName() != null || dto.getAuthor() != null) {
            return findByNameAndAuthor(dto.getName(), dto.getAuthor());

        }

        return null;
    }



    public ResourceDTO findByNameAndAuthor(String name, String author) {
        List<ResourceDTO> repoUsers = findAll();

        Optional<List<ResourceDTO>> list = Optional.ofNullable(repoUsers.stream()
                .filter(dto ->
                        dto.getName().equals(name)
                                &&
                                dto.getAuthor().equals(author))
                .toList());

        if (list.isEmpty()) {
            return null;
        }

        if (list.get().size() > 1) {
            return list.get().get(0);
        }

        if (list.get().size() == 0) {
            return null;
        }

        return list.get().get(0);
    }

    // ----------------- Helper -----------------

}
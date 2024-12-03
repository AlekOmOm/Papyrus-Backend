package com.alek0m0m.papyrusbackend.ressource;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceService extends BaseService<ResourceDTOInput, ResourceDTO, Resource, ResourceMapper, ResourceRepository> {

    private final ResourceRepository repository;

    @Autowired
    public ResourceService(ResourceRepository repository, ResourceMapper mapper, ResourceRepository resourceRepository) {
        super(repository, mapper);
        this.repository = resourceRepository;
    }

    @Override
    protected void resetIncrement() {
        System.out.println("resource resetIncrement");
    }


    @Transactional
    public ResourceDTO save(ResourceDTO input) {

        // filter added to prevent duplicate users
        List<ResourceDTO> existingResources = findByNameAndAuthor(input.getName(), input.getAuthor());
        if (!existingResources.isEmpty()) {
            return existingResources.get(0);
        }

        // if no duplicate users, save the user
        return super.save(input);
    }


    public List<ResourceDTO> findByNameAndAuthor(String name, String author) {
        List<ResourceDTO> repoUsers = getDtoMapper().mapToDTOs(getRepository().findAll()).stream().toList();

        return repoUsers.stream()
                .filter(dto ->
                        dto.getName().equals(name)
                                &&
                                dto.getAuthor().equals(author))
                .toList();


    }

}
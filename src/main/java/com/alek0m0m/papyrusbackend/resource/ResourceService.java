package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.exception.ResourceNotFoundException;
import com.alek0m0m.papyrusbackend.exception.UserNotFoundException;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService extends BaseService<ResourceDTOInput, ResourceDTO, Resource, ResourceMapper, ResourceRepository> {

    private final ResourceRepository repository;
    private final ResourceMapper mapper;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository repository, ResourceMapper mapper, UserRepository userRepository, ResourceRepository resourceRepository) {
        super(repository, mapper);
        this.repository = resourceRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
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
            return findById(dto.getId());
        }

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

    // ----------------- Business Operations -----------------


    @Transactional
    public ResourceDTO savePersonalResource(Long resourceId, Long userId) {
        Resource resource = repository.findById(resourceId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        System.out.println("resource: "+resource);
        System.out.println("user: "+user);

        resource.getUsers().add(user);
        user.getSavedResources().add(resource);

        repository.save(resource);
        userRepository.save(user);

        return mapper.convert(resource);
    }

    public List<ResourceDTO> save(List<ResourceDTO> savedResources) {
        return savedResources.stream()
                .map(this::save)
                .toList();
    }

    public List<ResourceDTO> searchResources(String query) {
        return resourceRepository.findByNameContainingIgnoreCase(query);
    }


    // ----------------- Helper -----------------

}
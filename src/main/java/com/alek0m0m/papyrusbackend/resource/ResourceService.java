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

@Service
public class ResourceService extends BaseService<ResourceDTOInput, ResourceDTO, Resource, ResourceMapper, ResourceRepository> {

    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;
    private final ResourceMapper resourceMapper;

    @Autowired
    public ResourceService(ResourceRepository repository, ResourceMapper mapper, ResourceRepository resourceRepository, UserRepository userRepository, ResourceMapper resourceMapper) {
        super(repository, mapper);
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.resourceMapper = resourceMapper;
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




    @Transactional
    public ResourceDTO savePersonalResource(Long resourceId, Long userId) {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        resource.getUsers().add(user);
        user.getResources().add(resource);

        resourceRepository.save(resource);
        userRepository.save(user);

        return resourceMapper.convert(resource);
    }



}
package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.field.FieldDTO;
import com.alek0m0m.papyrusbackend.field.FieldService;
import com.alek0m0m.papyrusbackend.resource.Resource;
import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import com.alek0m0m.papyrusbackend.resource.ResourceRepository;
import com.alek0m0m.papyrusbackend.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<UserDTOInput, UserDTO, User, UserMapper, UserRepository> {

    private final ResourceService resourceService;
    private final FieldService fieldService;
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final ResourceRepository resourceRepository;

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper, ResourceService resourceService, FieldService fieldService, UserRepository userRepository, ResourceRepository resourceRepository) {
        super(repository, mapper);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.fieldService = fieldService;
        this.resourceService = resourceService;
        this.resourceRepository = resourceRepository;
    }

    @Override
    protected void resetIncrement() {
        //getRepository().resetAutoIncrement();
    }

    public UserDTO save(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        printUserSavedResources(dto);

        UserDTO existingUser = find(dto);

        if (existingUser != null) {
            dto = mapper.map(dto, existingUser.toEntity());
        }

        setChildren(dto, existingUser);
        return super.save(dto);
    }

    private void printUserSavedResources(UserDTO dto) {
        List<String> uniqueNames = dto.getSavedResources().stream()
                .map(ResourceDTO::getName)
                .distinct()
                .collect(Collectors.toList());

        for (ResourceDTO resource : dto.getSavedResources()) {
            System.out.println(resource.getName());
        }
    }


    // ----------------- Main Business Operations -----------------

    @Transactional
    public void addUserResourceRelation(Long userId, Long resourceId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(() -> new IllegalArgumentException("Resource not found"));

        boolean relationExists = user.getSavedResources().stream()
                .anyMatch(savedResource
                        -> savedResource.getName().equals(resource.getName())
                        && savedResource.getAuthor().equals(resource.getAuthor()) //TODO: validate check attributes: name and author
                );

        if (relationExists) {
            return;
        }

        user.getSavedResources().add(resource);
        userRepository.saveAndFlush(user);
    }

    // ----------------- CRUD -----------------

    public UserDTO find(UserDTO userDTO) {
        if (userDTO.getId() != null && userDTO.getId() != 0) {
            System.out.println("userDTO.getId() != null: "+userDTO.getId());
            return findByIdWithFieldAndResources(userDTO.getId());
        }

        if (userDTO.getName() != null && userDTO.getEmail() != null) {
            return findByNameAndEmail(userDTO.getName(), userDTO.getEmail());
        }

        return null;
    }

    @Transactional
    public UserDTO findByIdWithFieldAndResources(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user == null) {
            return null;
        }

        UserDTO dtoClean = new UserDTO(user.get());

        UserDTO dto = setField(new UserDTO(user.get()));

        return new UserDTO(user.get());
    }

    public UserDTO findByNameAndEmail(String name, String email) {
        List<UserDTO> repoUsers = findAll();

        Optional<List<UserDTO>> user = Optional.ofNullable(repoUsers.stream()
                .filter(userDTO ->
                        userDTO.getName().equals(name)
                                &&
                                userDTO.getEmail().equals(email))
                .toList());

        if (user.isEmpty()) {
            return null;
        }

        if (user.get().size() > 1) {
            return setField(user.get().get(0));
        }

        if (user.get().size() == 0) {
            return null;
        }

        return setField(user.get().get(0));
    }


    // ----------------- Helper -----------------

    private UserDTO setField(UserDTO user) {
        FieldDTO field = fieldService.find(user.getField());
        return user.setField(field);
    }

    private void setChildren(UserDTO dto, UserDTO existingUser) {
        if (existingUser == null) {
            return;
        }
        dto.setField(fieldService.save(existingUser.getField()));

        List<ResourceDTO> res = existingUser.getSavedResources().stream()
                .distinct() // remove duplicates
                .collect(Collectors.toList());

        dto.setSavedResources(resourceService.save(res)
        );
    }

}
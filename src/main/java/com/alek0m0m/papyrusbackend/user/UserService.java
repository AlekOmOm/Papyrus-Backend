package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.field.FieldDTO;
import com.alek0m0m.papyrusbackend.field.FieldService;
import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import com.alek0m0m.papyrusbackend.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<UserDTOInput, UserDTO, User, UserMapper, UserRepository> {

    private final ResourceService resourceService;
    private final FieldService fieldService;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper, ResourceService resourceService, FieldService fieldService, UserRepository userRepository) {
        super(repository, mapper);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.fieldService = fieldService;
        this.resourceService = resourceService;
    }

    @Override
    protected void resetIncrement() {
        getRepository().resetAutoIncrement();
    }


    public UserDTO save(UserDTO entityDTO) {

        UserDTO existingUser = findByNameAndEmail(entityDTO.getName(), entityDTO.getEmail()).stream().findFirst().orElse(null);

        if (existingUser != null) {
            existingUser = mapper.map(entityDTO, existingUser.toEntity());
        }

        return super.save(entityDTO);
    }

    // ----------------- Main Business Operations -----------------

    @Transactional
    public void addUserResourceRelation(Long userId, Long resourceId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        ResourceDTO resource = resourceService.findById(resourceId);

        user.getSavedResources().add(resource.toEntity());
        userRepository.save(user);
    }









    // ----------------- CRUD -----------------

    public List<UserDTO> findByNameAndEmail(String name, String email) {
        List<UserDTO> repoUsers = findAll();

        return repoUsers.stream()
                .filter(userDTO ->
                        userDTO.getName().equals(name)
                                &&
                                userDTO.getEmail().equals(email))
                .toList();
    }


    // ----------------- Helper -----------------

    // print @Version of User and Resource
    public void printVersions(List<ResourceDTO> resources) {
        resources.forEach(resourceDTO -> System.out.println("Resource " + resourceDTO.getName() + " has version " + resourceDTO.getVersion()));
    }
}
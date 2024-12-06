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
import java.util.Optional;
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
        //getRepository().resetAutoIncrement();
    }


    public UserDTO save(UserDTO entityDTO) {

        UserDTO existingUser = find(entityDTO);

        if (existingUser != null) {
            System.out.println("User already exists: " + existingUser.getName());
            existingUser = mapper.map(entityDTO, existingUser.toEntity());
            return super.update(existingUser);
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
        UserDTO user = findById(userId);

        return setField(user);
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
            System.out.println("   More than one user found with name: " + name + " and email: " + email);
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

    // print @Version of User and Resource
    public void printVersions(List<ResourceDTO> resources) {
        resources.forEach(resourceDTO -> System.out.println("Resource " + resourceDTO.getName() + " has version " + resourceDTO.getVersion()));
    }
}
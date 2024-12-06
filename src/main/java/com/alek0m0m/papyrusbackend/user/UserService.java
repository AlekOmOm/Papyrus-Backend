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
import java.util.List;
import java.util.Optional;
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


    public UserDTO save(UserDTO entityDTO) {
        if (entityDTO == null) {
            return null;
        }

        UserDTO existingUser = find(entityDTO);

        if (existingUser != null) {
            existingUser = mapper.map(entityDTO, existingUser.toEntity());
            return super.update(existingUser);
        }

        return super.save(entityDTO);
    }

    // ----------------- Main Business Operations -----------------

    @Transactional
    public void addUserResourceRelation(Long userId, Long resourceId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Optional<Resource> resource = resourceRepository.findById(resourceId);

        user.getSavedResources().add(resource.get());
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
        Optional<User> user = userRepository.findById(userId);

        if (user == null) {
            return null;
        }

        System.out.println("findByIdWithFieldAndResources: "+user.get().getSavedResources().size());


        UserDTO dtoClean = new UserDTO(user.get());
        System.out.println("findByIdWithFieldAndResources: "+dtoClean.getSavedResources().size());

        UserDTO dto = setField(new UserDTO(user.get()));

        System.out.println("findByIdWithFieldAndResources: "+dto.getSavedResources().size());

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

}
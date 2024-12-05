package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.field.FieldDTO;
import com.alek0m0m.papyrusbackend.field.FieldService;
import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import com.alek0m0m.papyrusbackend.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<UserDTOInput, UserDTO, User, UserMapper, UserRepository> {

    private final ResourceService resourceService;
    private final FieldService fieldService;

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper, ResourceService resourceService, FieldService fieldService) {
        super(repository, mapper);
        this.resourceService = resourceService;
        this.fieldService = fieldService;
    }

    @Override
    protected void resetIncrement() {
        getRepository().resetAutoIncrement();
    }


    @Transactional
    public UserDTO save(UserDTO input) {

        if (input.getName() == null) {
            return null;
        }

        List<UserDTO> existingUsers = findByNameAndEmail(input.getName(), input.getEmail());
        if (!existingUsers.isEmpty()) {
            UserDTO existingUser = existingUsers.get(0);

            input.setId(existingUser.getId());
        }

        //  sets field
        FieldDTO managedField = fieldService.find(input.getName(), input.getField());
        input.setField(managedField);

        fieldService.save(input.getField());

        //  sets saved resources
        List<ResourceDTO> managedResources = input.getSavedResources().stream()
                .map(resourceDTO -> resourceService.findById(resourceDTO.getId()))
                .collect(Collectors.toList());
        input.setSavedResources(managedResources);


        return super.save(input);
    }

    public List<UserDTO> findByNameAndEmail(String name, String email) {
        List<UserDTO> repoUsers = findAll();

        return repoUsers.stream()
                .filter(userDTO ->
                        userDTO.getName().equals(name)
                                &&
                                userDTO.getEmail().equals(email))
                .toList();
    }

}
package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService extends BaseService<UserDTOInput, UserDTO, User, UserMapper, UserRepository> {

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void resetIncrement() {
        getRepository().resetAutoIncrement();
    }


    @Transactional
    public UserDTO save(UserDTO input) {

        // filter added to prevent duplicate users
        if (input.getName() == null) {
            return null;
        }

        List<UserDTO> existingUsers = findByNameAndEmail(input.getName(), input.getEmail());
        if (!existingUsers.isEmpty()) {
            return existingUsers.get(0);
        }

        // if no duplicate users, save the user
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
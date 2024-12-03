package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService extends BaseService<UserDTOInput, UserDTO, User, UserMapper, UserRepository> {

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }



    @Transactional
    public UserDTO save(UserDTOInput input) {
        List<UserDTO> existingUsers = findByNameAndEmail(input.getName(), input.getEmail());
        if (!existingUsers.isEmpty()) {
            return existingUsers.get(0);
        }
        return super.save(getDtoMapper().toDTO(input));
    }


    public List<UserDTO> findByNameAndEmail(String name, String email) {
        List<UserDTO> repoUsers = getDtoMapper().mapToDTOs(getRepository().findAll()).stream().toList();

        System.out.println("UserService.findByNameAndEmail: ");
        repoUsers.forEach(System.out::println);

        return repoUsers.stream()
                .filter(userDTO ->
                        userDTO.getName().equals(name)
                                &&
                                userDTO.getEmail().equals(email))
                .toList();


    }


}
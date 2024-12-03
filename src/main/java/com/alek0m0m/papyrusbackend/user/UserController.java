package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseRESTController<UserDTOInput, UserDTO, User, UserMapper, UserService, UserRepository> {

    @Autowired
    public UserController(UserService service) {
        super(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") long id, @RequestBody UserDTOInput dtoinput) {
        UserDTO existingEntity = this.getService().findById(id);
        if (existingEntity == null) {
            return ResponseEntity.notFound().build();
        }
        UserDTO updatedEntity = mapper.convert(dtoinput);
                updatedEntity.setId(id); // Ensure the ID is set correctly
        return ResponseEntity.ok(this.getService().save(updatedEntity));
    }



}



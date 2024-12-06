package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import com.alek0m0m.papyrusbackend.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController extends BaseRESTController<UserDTOInput, UserDTO, User, UserMapper, UserService, UserRepository> {

    private final ResourceService resourceService;

    @Autowired
    public UserController(UserService service, ResourceService resourceService) {
        super(service);
        this.resourceService = resourceService;
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

    @PostMapping("/saveaspersonalresource")
    public ResponseEntity<ResourceDTO> saveAsPersonalResource(@RequestParam Long resourceId, @RequestParam Long userId) {
        ResourceDTO savedResource = resourceService.savePersonalResource(resourceId, userId);
        return ResponseEntity.ok(savedResource);
    }

    @GetMapping("/{id}/resources")
    public ResponseEntity<Iterable<ResourceDTO>> getResources(@PathVariable("id") long id) {
        System.out.println("id: "+id);
        UserDTO user = service.findByIdWithFieldAndResources(id);

        System.out.println("user: "+user.getSavedResources());

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getSavedResources());
    }

}



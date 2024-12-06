package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController extends BaseRESTController<ResourceDTOInput, ResourceDTO, Resource, ResourceMapper, ResourceService, ResourceRepository> {

    @Autowired
    public ResourceController(ResourceService service) {
        super(service);
    }



    /*
    @GetMapping("{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable Long id){
        ResourceDTO resourceDTO = service.findById(id);
        return ResponseEntity.ok(resourceDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAllResources(){
        List<ResourceDTO> resourceDTOs = resourceDTOs.findAll();
        return ResponseEntity.ok(resourceDTOs);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResourceDTO>> searchResources(@RequestParam String name, @RequestParam String author){
        List<ResourceDTO> resourceDTOs = service.findByNameAndAuthor(name, author);
        return ResponseEntity.ok(resourceDTOs);
    }

    /*@PutMapping("{id}")
    public ResponseEntity<ResourceDTO> updateResource(@PathVariable Long id, @RequestBody ResourceDTO resourceDTO){
        ResourceDTO updatedResource = resourceService.update(id, resourceDTO);
        return ResponseEntity.ok(updatedResource);
    }/*

    /*@DeleteMapping("{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id){
        resourceService.delete(id);
        return ResponseEntity.ok().build();
    }*/


}



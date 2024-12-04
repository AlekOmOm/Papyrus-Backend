package com.alek0m0m.papyrusbackend.ressource;

import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController extends BaseRESTController<ResourceDTOInput, ResourceDTO, Resource, ResourceMapper, ResourceService, ResourceRepository> {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService service, ResourceService resourceService) {
        super(service);
        this.resourceService = resourceService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResourceDTO> saveResouce(@RequestParam ResourceDTO resourceDTO){
        ResourceDTO savedResource = resourceService.save(resourceDTO);
        return ResponseEntity.ok(savedResource);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable Long id){
        ResourceDTO resourceDTO = resourceService.findById(id);
        return ResponseEntity.ok(resourceDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAllResources(){
        List<ResourceDTO> resourceDTOs = resourceService.findAll();
        return ResponseEntity.ok(resourceDTOs);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResourceDTO>> searchResources(@RequestParam String name, @RequestParam String author){
        List<ResourceDTO> resourceDTOs = resourceService.findByNameAndAuthor(name, author);
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



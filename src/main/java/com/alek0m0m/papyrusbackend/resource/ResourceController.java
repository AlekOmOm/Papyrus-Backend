package com.alek0m0m.papyrusbackend.resource;

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


    @GetMapping("/search")
    public ResponseEntity<List<ResourceDTO>> searchResources(@RequestParam String query) {
        List<ResourceDTO> resources = resourceService.searchResources(query);
        return ResponseEntity.ok(resources);
    }


}



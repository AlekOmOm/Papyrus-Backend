package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resources")
public class ResourceController extends BaseRESTController<ResourceDTOInput, ResourceDTO, Resource, ResourceMapper, ResourceService, ResourceRepository> {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService service, ResourceService resourceService) {
        super(service);
        this.resourceService = resourceService;
    }

    @GetMapping("/check-uniqueness")
    public ResponseEntity<Map<String, Boolean>> checkUniqueness(@RequestParam String name, @RequestParam("ref_id") String refId) {
        boolean isUnique = service.isUnique(name, refId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isUnique", isUnique);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/search")
    public ResponseEntity<List<ResourceDTO>> searchResources(@RequestParam String query) {
        List<ResourceDTO> resources = resourceService.searchResources(query);
        return ResponseEntity.ok(resources);
    }


}



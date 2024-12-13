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

    @Autowired
    public ResourceController(ResourceService service) {
        super(service);
    }

    @GetMapping("/check-uniqueness")
    public ResponseEntity<Map<String, Boolean>> checkUniqueness(@RequestParam String name, @RequestParam("ref_id") String refId) {
        boolean isUnique = service.isUnique(name, refId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isUnique", isUnique);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Resource>> getRecentResources() {
        List<Resource> recentResourcesesources = service.getRecentResources();
        return ResponseEntity.ok(recentResourcesesources);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Resource>> searchResources(@RequestParam String query) {
        List<Resource> resources = service.searchResources(query);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/saved/search")
    public ResponseEntity<List<Resource>> searchSavedResources(@RequestParam String query) {
        List<Resource> resources = service.searchSavedResources(query);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/recent/search")
    public ResponseEntity<List<Resource>> searchRecentResources(@RequestParam String query) {
        List<Resource> resources = service.searchRecentResources(query);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/project/search")
    public ResponseEntity<List<Resource>> searchProjectResources(@RequestParam String query) {
        List<Resource> resources = service.searchProjectResources(query);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/topics/search")
    public ResponseEntity<List<Resource>> searchTopicsResources(@RequestParam String query) {
        List<Resource> resources = service.searchTopicsResources(query);
        return ResponseEntity.ok(resources);
    }
}



package com.alek0m0m.papyrusbackend.resource;

import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resources")
public class ResourceController extends BaseRESTController<ResourceDTOInput, ResourceDTO, Resource, ResourceMapper, ResourceService, ResourceRepository> {

    @Autowired
    public ResourceController(ResourceService service) {
        super(service);
    }


}



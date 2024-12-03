package com.alek0m0m.papyrusbackend.field;


import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fields")
public class FieldController extends BaseRESTController<FieldDTOInput, FieldDTO, Field, FieldMapper, FieldService, FieldRepository> {

    @Autowired
    public FieldController(FieldService service) {
        super(service);
    }


}



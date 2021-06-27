package com.matrix.tech.backend.apirest.interfaces.technology.create.web;

import com.matrix.tech.backend.apirest.interfaces.technology.BaseTechnologyController;
import com.matrix.tech.backend.apirest.technology.domain.Technology;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaverTechnologyController extends BaseTechnologyController {

    @PostMapping("/")
    public Technology create(@RequestBody Technology technology) {
        this.technologyService.save(technology);
        return technology;
    }

}
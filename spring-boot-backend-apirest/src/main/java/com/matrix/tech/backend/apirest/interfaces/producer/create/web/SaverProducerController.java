package com.matrix.tech.backend.apirest.interfaces.producer.create.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.CREATE;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.interfaces.producer.BaseProducerController;
import com.matrix.tech.backend.apirest.producer.domain.Producer;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaverProducerController extends BaseProducerController {

    @PostMapping("/")
    public ResponseEntity create(@Valid @RequestBody Producer producer, BindingResult result) {

        if (Util.hasErrors(result)) {
            return Util.obtainErrorResponse(result);
        }

        Producer newProducer = null;

        try {

            newProducer = this.producerService.save(producer);

        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, CREATE);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El productor ha sido creado con éxito!");
        response.put("productor", newProducer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}

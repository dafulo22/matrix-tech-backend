package com.matrix.tech.backend.apirest.interfaces.producer.update.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.UPDATE;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.interfaces.producer.BaseProducerController;
import com.matrix.tech.backend.apirest.producer.domain.Producer;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdaterProducerController extends BaseProducerController {

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Producer producerParam, BindingResult result,
        @PathVariable Long id) {

        if (Util.hasErrors(result)) {
            return Util.obtainErrorResponse(result);
        }

        ResponseEntity<?> producer = findProducerById(id);

        if (!Util.verifyResponse(producer)) {
            return producer;
        }

        Producer producerUpdated = null;
        Producer currentProducer = (Producer) producer.getBody();

        try {

            Util.fillInDefaultInformation(producerParam, currentProducer);

            producerUpdated = this.producerService.save(currentProducer);


        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, UPDATE);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El productor ha sido actualizado con éxito!");
        response.put("productor", producerUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}

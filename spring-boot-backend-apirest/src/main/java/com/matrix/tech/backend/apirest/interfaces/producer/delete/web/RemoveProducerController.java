package com.matrix.tech.backend.apirest.interfaces.producer.delete.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.DELETE;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.interfaces.producer.BaseProducerController;
import com.matrix.tech.backend.apirest.producer.domain.Producer;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveProducerController extends BaseProducerController {

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseEntity<?> producerResponse = findProducerById(id);

        if (!Util.verifyResponse(producerResponse)) {
            return producerResponse;
        }

        Producer currentProducer = (Producer) producerResponse.getBody();

        try {
            this.producerService.delete(currentProducer);
        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, DELETE);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Productor eliminado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

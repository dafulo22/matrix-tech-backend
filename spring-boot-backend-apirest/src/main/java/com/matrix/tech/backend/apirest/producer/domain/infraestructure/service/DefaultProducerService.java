package com.matrix.tech.backend.apirest.producer.domain.infraestructure.service;

import com.matrix.tech.backend.apirest.producer.domain.Producer;
import com.matrix.tech.backend.apirest.producer.domain.infraestructure.persistence.IProducerDao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultProducerService implements IProducerService {

    @Autowired
    private IProducerDao producerDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producer> findAll() {
        return (List<Producer>) producerDao.findAll();
    }

    @Override
    @Transactional
    public Producer save(Producer productor) {
        return producerDao.save(productor);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producer> findById(Long id) {
        return producerDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(Producer productor) {
        producerDao.delete(productor);

    }

}

package com.matrix.tech.backend.apirest.technology.domain.infraestructure.service;

import com.matrix.tech.backend.apirest.technology.domain.Technology;
import com.matrix.tech.backend.apirest.technology.domain.infraestructure.persistence.ITechnologyDao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultTechnologyService implements ITechnologyService {

    @Autowired
    private ITechnologyDao technologyDao;

    @Override
    @Transactional(readOnly = true)
    public List<Technology> findAll() {
        return (List<Technology>) technologyDao.findAll();
    }

    @Override
    @Transactional
    public void save(Technology tecnologia) {
        technologyDao.save(tecnologia);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Technology> findById(Long id) {
        return technologyDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(Technology tecnologia) {
        technologyDao.delete(tecnologia);

    }

}

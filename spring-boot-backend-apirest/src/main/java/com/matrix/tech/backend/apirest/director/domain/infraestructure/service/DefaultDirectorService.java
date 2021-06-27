package com.matrix.tech.backend.apirest.director.domain.infraestructure.service;

import com.matrix.tech.backend.apirest.director.domain.Director;
import com.matrix.tech.backend.apirest.director.domain.infraestructure.persistence.IDirectorDao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultDirectorService implements IDirectorService {

    @Autowired
    private IDirectorDao directorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Director> findAll() {
        return (List<Director>) directorDao.findAll();
    }

    @Override
    @Transactional
    public Director save(Director director) {
        return directorDao.save(director);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Director> findById(Long id) {
        return directorDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(Director director) {
        directorDao.delete(director);

    }

}

package com.matrix.tech.backend.apirest.game.domain.infraestructure.service;

import com.matrix.tech.backend.apirest.game.domain.Game;
import com.matrix.tech.backend.apirest.game.domain.infraestructure.persistence.IGameDao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultGameService implements IGameService {

    @Autowired
    private IGameDao gameDao;

    @Override
    @Transactional(readOnly = true)
    public List<Game> findAll() {
        return (List<Game>) gameDao.findAll();
    }

    @Override
    @Transactional
    public Game save(Game cliente) {
        return gameDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Game> findById(Long id) {
        return gameDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(Game cliente) {
        gameDao.delete(cliente);

    }

}

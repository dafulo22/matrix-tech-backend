package com.matrix.tech.backend.apirest.interfaces.game.read.web;

import com.matrix.tech.backend.apirest.game.domain.Game;
import com.matrix.tech.backend.apirest.interfaces.game.BaseGameController;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderGameController extends BaseGameController {

    @GetMapping("/")
    public List<Game> index() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        return findGameById(id);
    }

}

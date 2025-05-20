package com.badminton.controller;

import com.badminton.converter.GameDTOConverter;
import com.badminton.dto.GameDTO;
import com.badminton.model.Game;
import com.badminton.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepository;
    private final GameDTOConverter gameDTOConverter;

    @Autowired
    public GameController(GameRepository gameRepository, GameDTOConverter gameDTOConverter) {
        this.gameRepository = gameRepository;
        this.gameDTOConverter = gameDTOConverter;
    }

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameDTO> games = gameRepository.findAll().stream()
                .map(gameDTOConverter::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        return gameRepository.findById(id)
                .map(gameDTOConverter::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO) {
        Game game = gameDTOConverter.toEntity(gameDTO);
        Game savedGame = gameRepository.save(game);
        return ResponseEntity.ok(gameDTOConverter.toDTO(savedGame));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        if (!gameRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gameDTO.setId(id);
        Game game = gameDTOConverter.toEntity(gameDTO);
        Game updatedGame = gameRepository.save(game);
        return ResponseEntity.ok(gameDTOConverter.toDTO(updatedGame));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        if (!gameRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 
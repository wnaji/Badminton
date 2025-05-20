package com.badminton.converter;

import com.badminton.dto.GameDTO;
import com.badminton.dto.UserDTO;
import com.badminton.model.Game;
import com.badminton.model.User;
import com.badminton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameDTOConverter {
    
    private final UserRepository userRepository;
    
    @Autowired
    public GameDTOConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public GameDTO toDTO(Game game) {
        if (game == null) {
            return null;
        }
        
        UserDTO player1DTO = UserDTO.builder()
                .id(game.getPlayer1().getId())
                .username(game.getPlayer1().getUsername())
                .build();
                
        UserDTO player2DTO = UserDTO.builder()
                .id(game.getPlayer2().getId())
                .username(game.getPlayer2().getUsername())
                .build();
                
        UserDTO winnerDTO = UserDTO.builder()
                .id(userRepository.findByUsername(game.getWinner()).get().getId())
                .username(game.getWinner())
                .build();
        
        return GameDTO.builder()
                .id(game.getId())
                .player1(player1DTO)
                .player2(player2DTO)
                .score_player1(game.getScore_player1())
                .score_player2(game.getScore_player2())
                .winner(winnerDTO)
                .date(game.getDate())
                .maxScore(game.getMaxScore())
                .build();
    }
    
    public Game toEntity(GameDTO dto) {
        if (dto == null) {
            return null;
        }
        
        User player1 = userRepository.findById(dto.getPlayer1().getId())
                .orElseThrow(() -> new IllegalArgumentException("Player 1 not found"));
        User player2 = userRepository.findById(dto.getPlayer2().getId())
                .orElseThrow(() -> new IllegalArgumentException("Player 2 not found"));
        User winner = userRepository.findById(dto.getWinner().getId())
                .orElseThrow(() -> new IllegalArgumentException("Winner not found"));
        
        Game game = new Game();
        game.setId(dto.getId());
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        game.setScore_player1(dto.getScore_player1());
        game.setScore_player2(dto.getScore_player2());
        game.setWinner(winner.getUsername());
        game.setDate(dto.getDate());
        game.setMaxScore(dto.getMaxScore());
        return game;
    }
} 
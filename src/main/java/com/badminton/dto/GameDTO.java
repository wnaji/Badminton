package com.badminton.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private Long id;

    @NotBlank(message = "Player 1 is required")
    private UserDTO player1;

    @NotBlank(message = "Player 2 is required")
    private UserDTO player2;

    @NotNull(message = "Score for player 1 is required")
    @Min(value = 0, message = "Score cannot be negative")
    private Integer score_player1;

    @NotNull(message = "Score for player 2 is required")
    @Min(value = 0, message = "Score cannot be negative")
    private Integer score_player2;

    @NotBlank(message = "Winner is required")
    private UserDTO winner;

    @NotNull(message = "Date is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date;

    @NotNull(message = "Max score is required")
    @Min(value = 1, message = "Max score must be at least 1")
    private Integer maxScore;
} 
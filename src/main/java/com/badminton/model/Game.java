package com.badminton.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"game\"", schema = "railway")
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player1", nullable = false)
    private User player1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player2", nullable = false)
    private User player2;

    @Column(name = "score_player1", nullable = false)
    private Integer score_player1;

    @Column(name = "score_player2", nullable = false)
    private Integer score_player2;

    @Column(name = "winner", nullable = false, length = 100)
    private String winner;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "max_score", nullable = false)
    private Integer maxScore;
} 
package com.badminton.repository;

import com.badminton.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // Basic CRUD operations are automatically provided by JpaRepository
} 
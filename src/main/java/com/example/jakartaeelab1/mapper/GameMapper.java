package com.example.jakartaeelab1.mapper;

import com.example.jakartaeelab1.dto.GameDto;
import com.example.jakartaeelab1.entity.Game;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GameMapper {

    public List<GameDto> map(List<Game> all) {
        return all.stream().map(game -> new GameDto(game.getId(), game.getName())).toList();
    }
}

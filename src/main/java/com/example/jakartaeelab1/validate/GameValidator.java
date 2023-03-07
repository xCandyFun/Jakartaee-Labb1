package com.example.jakartaeelab1.validate;

import com.example.jakartaeelab1.entity.Game;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameValidator {

    public boolean validate(Game game){
        return game.getName() != null && !game.getName().isEmpty();

    }

}

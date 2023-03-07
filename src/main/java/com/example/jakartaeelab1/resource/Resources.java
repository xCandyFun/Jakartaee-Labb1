package com.example.jakartaeelab1.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

@ApplicationScoped
public class Resources {

    @Produces
    public Jsonb createJsonb(){
        return JsonbBuilder.create();
    }

}

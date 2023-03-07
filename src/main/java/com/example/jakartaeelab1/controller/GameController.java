package com.example.jakartaeelab1.controller;

import com.example.jakartaeelab1.dto.GameDto;
import com.example.jakartaeelab1.entity.Game;
import com.example.jakartaeelab1.mapper.GameMapper;
import com.example.jakartaeelab1.repository.GameRepository;
import com.example.jakartaeelab1.validate.GameValidator;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GameController {

    @Inject
    GameRepository repository;
    @Inject
    GameValidator validator;

    @Inject
    GameMapper mapper;

    @GET
    public List<GameDto> getAll(@QueryParam("name") String name){
        if (name == null)
            return mapper.map(repository.findAll());

        return mapper.map(repository.findAllByName(name));
    }

    @GET
    @Path("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns game object",
                    content = @Content(schema = @Schema(implementation = GameDto.class))),
            @ApiResponse(responseCode = "404", description = "Id not found")})
    public Response getOne(@PathParam("id") Long id){
        var game = repository.findOne(id);
        if (game.isPresent())
            return Response.ok().entity(game.get()).build();
        //return Response.status(404).build();
        //throw new IdNotFoundException("Not found Id: "+ id);
        throw new NotFoundException("id: "+id);

    }

    @POST
    public Response addOne(@Valid Game game){
        repository.insertGame(game);
        return Response.created(URI.create("game/"+ game.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteOne(@PathParam("id") Long id){
        repository.deleteGame(id);

    }

    @PUT
    public void updateGame(@QueryParam("id") Long id, @QueryParam("name") String name) {
        repository.update(id, name);
    }

}


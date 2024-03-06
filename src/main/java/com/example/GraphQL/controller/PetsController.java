package com.example.GraphQL.controller;

import com.example.GraphQL.model.Owner;
import com.example.GraphQL.model.Pet;
import com.example.GraphQL.service.OwnerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;


@Controller
public class PetsController {

    private final OwnerService ownerService;

    public PetsController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @QueryMapping
    public List<Owner> owners(){
        return ownerService.findAll();
    }

    @QueryMapping
    public Optional<Owner> owner(@Argument Integer id){
        return ownerService.findOne(id);
    }

    @MutationMapping
    public Owner create(@Argument String name,@Argument Pet pet){
        return ownerService.create(name,pet);
    }

    @MutationMapping
    public Owner update(@Argument Integer id, @Argument String name,@Argument Pet pet){
        return ownerService.update(id,name,pet);
    }

    @MutationMapping
    public Owner delete(@Argument Integer id){
        return ownerService.delete(id);
    }

}

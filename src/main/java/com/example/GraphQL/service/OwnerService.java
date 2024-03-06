package com.example.GraphQL.service;

import com.example.GraphQL.model.Owner;
import com.example.GraphQL.model.Pet;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OwnerService {

    private final List<Owner> owners = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<Owner> findAll(){
        return owners;
    }

    public Optional<Owner> findOne(Integer id){
        return owners.stream().filter(owner -> owner.id().equals(id)).findFirst();
    }

    public Owner create(String name, Pet pet){
        Owner owner = new Owner(id.incrementAndGet(),name,pet);
        owners.add(owner);
        return owner;
    }

    public Owner delete(Integer id){
     Owner owner = owners.stream().filter(c -> c.id().equals(id)).findFirst().orElseThrow(IllegalArgumentException::new);
     owners.remove(owner);
     return owner;
    }

    public Owner update(Integer id,String name, Pet pet){

        Owner updatedOwner = new Owner(id,name,pet);

        Optional<Owner> optional = owners.stream().filter(c -> c.id().equals(id)).findFirst();

        if(optional.isPresent()){
            Owner owner = optional.get();
            int index = owners.indexOf(owner);
            owners.set(index,updatedOwner);
        }
        else {
            throw new IllegalArgumentException("Invalid Owner");
        }
        return updatedOwner;
    }

    @PostConstruct
    private void init(){
        owners.add(new Owner(id.incrementAndGet(), "Olivia", new Pet("Milo", "spotted")));
        owners.add(new Owner(id.incrementAndGet(), "William", new Pet("Luna", "silver")));
        owners.add(new Owner(id.incrementAndGet(), "Ava", new Pet("Rocky", "brindle")));
        owners.add(new Owner(id.incrementAndGet(), "James", new Pet("Sadie", "tan")));
        owners.add(new Owner(id.incrementAndGet(), "Isabella", new Pet("Oscar", "cream")));
    }
}

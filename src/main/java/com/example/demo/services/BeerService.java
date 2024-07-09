package com.example.demo.services;

import com.example.demo.models.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);

    List<Beer> listBeers();



    Beer saveNewBeer(Beer beer);

    Beer deleteBeerById(UUID id);



    Beer updateBeer(UUID id, Beer beer);

    void patchBeerById(UUID id, Beer beer);
}

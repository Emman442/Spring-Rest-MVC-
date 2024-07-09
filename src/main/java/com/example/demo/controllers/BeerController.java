package com.example.demo.controllers;


import com.example.demo.models.Beer;
import com.example.demo.services.BeerService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;
    @RequestMapping(method = RequestMethod.GET , value = "{id}")
    public Beer getBeerById(@PathVariable("id") UUID id){
        log.debug("Get Beer By Id -in Controllersss");
        return beerService.getBeerById(id);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List <Beer> listBeers(){
        return beerService.listBeers();
    }

//    @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity handlePost(@RequestBody Beer beer){
        Beer savedBeer = beerService.saveNewBeer(beer);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity deleteBeer(@PathVariable("id") UUID id){

         beerService.deleteBeerById(id);
         return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    @PutMapping("{id}")
    public ResponseEntity updateBeerById(@PathVariable("id") UUID id, @RequestBody
                                         Beer beer){
        beerService.updateBeer(id, beer);

        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{id}")
    public ResponseEntity updateBeerByPatchId(@PathVariable("id") UUID id, @RequestBody
    Beer beer){
        beerService.patchBeerById(id, beer);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

package com.example.demo.services;

import com.example.demo.models.Beer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService{

    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl(){
        this.beerMap= new HashMap<>();

        Beer beer1 = Beer.builder().id(UUID.randomUUID()).beerName("GalaxyCat").version(1).upc("12345").price(new BigDecimal("12.68")).quantityOnHand(22).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now())
                .build();
        Beer beer2 = Beer.builder().id(UUID.randomUUID()).beerName("Hamster").version(1).upc("135564").price(new BigDecimal("45.76")).quantityOnHand(423).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now())
                .build();
        Beer beer3 = Beer.builder().id(UUID.randomUUID()).beerName("Bull Doge").version(1).upc("145588").price(new BigDecimal("68.23")).quantityOnHand(802).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public Beer getBeerById(UUID id){
        log.debug("Get Beer By ID Service was called");
        return beerMap.get(id);
    }

    @Override
    public List<Beer> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer saveNewBeer(Beer beer){
        Beer  savedBeer = Beer.builder().id(UUID.randomUUID()).beerName(beer.getBeerName()).version(beer.getVersion()).quantityOnHand(beer.getQuantityOnHand()).price(beer.getPrice()).upc(beer.getUpc()).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        beerMap.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    };

    @Override
    public Beer deleteBeerById(UUID id){
        Beer beerToDelete = beerMap.get(id);
         beerMap.remove(id, beerToDelete);
        return null;
    }

    @Override
    public Beer updateBeer(UUID id, Beer beer) {
        Beer beerToDelete = beerMap.get(id);
        beerToDelete.setBeerName(beer.getBeerName());
        beerToDelete.setPrice(beer.getPrice());
        beerToDelete.setQuantityOnHand(beer.getQuantityOnHand());
        beerToDelete.setPrice(beer.getPrice());
//        beerToDelete.setVersion(beer.getVersion());

        beerMap.put(beerToDelete.getId(), beerToDelete);
        return null;
    }

    @Override
    public void patchBeerById(UUID id, Beer beer){
        Beer existing = beerMap.get(id);

        if(StringUtils.hasText(beer.getBeerName())){
            existing.setBeerName(beer.getBeerName());
        };

        if(beer.getPrice() !=null){
            existing.setPrice(beer.getPrice());
        };
        if(beer.getQuantityOnHand() !=null){
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        };
        if(StringUtils.hasText(beer.getUpc())){
            existing.setUpc(beer.getUpc());
        }

    }
}

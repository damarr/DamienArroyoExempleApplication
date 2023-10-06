package com.damienarroyoexemple.damienarroyoexemplebackend.controller

import com.damienarroyoexemple.damienarroyoexemplebackend.model.Starship
import com.damienarroyoexemple.damienarroyoexemplebackend.model.StarshipModelView
import com.damienarroyoexemple.damienarroyoexemplebackend.service.StarshipService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Long.parseLong
import java.util.*


@RestController
@RequestMapping("/api/")
class StarshipController(val starshipService: StarshipService){

    @CrossOrigin
    @GetMapping("/starships")
    fun getStarshipByFilters(maxPrice: String, starshipClass: String) : List<StarshipModelView>{
        val results = starshipService.getAllStarships();
        var convertedResults : List<StarshipModelView> = convertToLong(results);
        var maxPriceLong : Long;

        if (maxPrice !== ""){
            maxPriceLong = parseLong(maxPrice);
            convertedResults = convertedResults.filter { s -> s.cost_in_credits != null && s.cost_in_credits <= maxPriceLong }
        }

        if (starshipClass != ""){
            convertedResults = convertedResults.filter { s -> s.starship_class!!.lowercase().contains(starshipClass.lowercase(Locale.getDefault())) }
        }
        return convertedResults;
    }

    private fun convertToLong(ships : List<Starship>) : List<StarshipModelView>{
        val convertModelViewList : MutableList<StarshipModelView> = mutableListOf();
        for (ship in ships){
            var shipCostLong : Long?

            if (ship.cost_in_credits != "unknown"){
                shipCostLong = parseLong(ship.cost_in_credits);
            }
            else {
                shipCostLong = null;
            }

            convertModelViewList.add(
                StarshipModelView(ship.name, ship.model, ship.starship_class, ship.manufacturer, shipCostLong , ship.crew, ship.passengers, ship.cargo_capacity)
            );
        }
        return convertModelViewList;
    }
}
package cl.rivendel.csv.controller;

import cl.rivendel.csv.model.scryfall.Card;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/api/v1")
public class TestController {

    @GetMapping(value = "/card/{cardId}")
    public Card getCard(@PathVariable("cardId") int id){
        //logic

        //return logic
        return new Card();
    }

}

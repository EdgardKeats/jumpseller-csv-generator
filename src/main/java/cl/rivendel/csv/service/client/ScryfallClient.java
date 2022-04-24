package cl.rivendel.csv.service.client;

import cl.rivendel.csv.model.scryfall.AllBulkData;
import cl.rivendel.csv.model.scryfall.BulkData;
import cl.rivendel.csv.model.scryfall.SetListObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "scryfallClient", url = "https://api.scryfall.com/")
public interface ScryfallClient {
    @RequestMapping(method = RequestMethod.GET, value = "/bulk-data")
    AllBulkData getBulkData();

    @RequestMapping(method = RequestMethod.GET, value = "/bulk-data/{id}", produces = "application/json")
    BulkData getBulkById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/bulk-data/{type}", produces = "application/json")
    BulkData getBulkByType(@PathVariable("type") String type);

    @RequestMapping(method = RequestMethod.GET, value = "/sets")
    SetListObject getAllSets();


}

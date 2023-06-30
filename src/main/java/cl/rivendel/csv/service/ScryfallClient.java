package cl.rivendel.csv.service;

import cl.rivendel.csv.model.scryfall.AllBulkData;
import cl.rivendel.csv.model.scryfall.BulkData;
import cl.rivendel.csv.model.scryfall.SetListObject;
import feign.Param;
import feign.RequestLine;

public interface ScryfallClient {

    @RequestLine("GET /bulk-data")
    AllBulkData getBulkData();

    @RequestLine("GET /bulk-data/{id}")
    BulkData getBulkById(@Param("id") String id);

    @RequestLine("GET /bulk-data/{type}")
    BulkData getBulkByType(@Param("type") String type);

    @RequestLine("GET /sets")
    SetListObject getAllSets();
}

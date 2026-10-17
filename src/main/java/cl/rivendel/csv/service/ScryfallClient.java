package cl.rivendel.csv.service;

import cl.rivendel.csv.model.scryfall.BulkMetaData;
import cl.rivendel.csv.model.scryfall.SetListObject;
import feign.RequestLine;

public interface ScryfallClient {

    @RequestLine("GET /bulk-data")
    BulkMetaData getBulkData();

    @RequestLine("GET /sets")
    SetListObject getAllSets();
}

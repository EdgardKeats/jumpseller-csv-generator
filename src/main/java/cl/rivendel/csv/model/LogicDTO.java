package cl.rivendel.csv.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogicDTO {
    private String setValue;
    private String setType;
    private String imbBBKey;
    private String dolarValue;
    private boolean uploadImages;
    private boolean addPrices;
}

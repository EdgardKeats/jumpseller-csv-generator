package cl.rivendel.csv.model.mtgjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Translations {
    @JsonProperty("Ancient Greek")
    private String ancientGreek;
    /*
    private String Arabic;
    private String "Chinese Simplified"?: string;
    private String "Chinese Traditional"?: string;
    private String French?: string;
    private String German?: string;
    private String Hebrew?: string;
    private String Italian?: string;
    private String Japanese?: string;
    private String Korean?: string;
    private String Latin?: string;
    private String Phyrexian?: string;
    private String "Portuguese (Brazil)"?: string;
    private String Russian?: string;
    private String Sanskrit?: string;
    private String Spanish?: string;
    */
}

package json.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductionCountry implements Serializable {
    @JsonProperty("iso_3166_1")
    String countryCode;

    @JsonProperty("name")
    String countryName;
}

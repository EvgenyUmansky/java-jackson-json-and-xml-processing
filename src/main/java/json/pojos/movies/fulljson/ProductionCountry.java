package json.pojos.movies.fulljson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class ProductionCountry implements Serializable {
    @JsonProperty("iso_3166_1")
    String countryCode;

    @JsonProperty("name")
    String countryName;
}

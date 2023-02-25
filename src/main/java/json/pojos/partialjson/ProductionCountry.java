package json.pojos.partialjson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@JsonIgnoreProperties
public class ProductionCountry implements Serializable {
    @JsonProperty("iso_3166_1")
    String countryCode;

    @JsonProperty("name")
    String countryName;
}

package json.pojos.movies.fulljson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class ProductionCompany implements Serializable {
    @JsonProperty("id")
    Integer productionCompanyId;

    @JsonProperty("logo_path")
    String logoPath;

    @JsonProperty("name")
    String productionCompanyName;

    @JsonProperty("origin_country")
    String originCountry;
}

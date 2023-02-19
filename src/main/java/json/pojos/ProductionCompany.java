package json.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductionCompany implements Serializable {
    @JsonProperty("id")
    Integer productionCompaniesId;

    @JsonProperty("logo_path")
    String logoPath;

    @JsonProperty("name")
    String productionCompaniesName;

    @JsonProperty("origin_country")
    String originCountry;
}

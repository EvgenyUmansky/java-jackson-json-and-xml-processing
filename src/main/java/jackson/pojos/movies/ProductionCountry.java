package jackson.pojos.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jackson.topics.views.MovieViews;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@JsonIgnoreProperties
public class ProductionCountry implements Serializable {
    @JsonView({MovieViews.ProductionCountries.class})
    @JsonProperty("iso_3166_1")
    String countryCode;

    @JsonView({MovieViews.ProductionCountryName.class})
    @JsonProperty("name")
    String countryName;
}

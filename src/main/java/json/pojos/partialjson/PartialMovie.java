package json.pojos.partialjson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@JsonIgnoreProperties
public class PartialMovie implements Serializable {

    // talk / overview JsonProperty with all its attributes
    @JsonProperty(value = "id", access = Access.READ_WRITE)
    Integer movieId;

    @JsonProperty("budget")
    Integer budget;

    @JsonProperty("genres")
    List<Genre> genres;

    @JsonProperty("original_language")
    String originalLanguage;

    @JsonProperty("overview")
    String overview;

    @JsonProperty("popularity")
    Float popularity;

    @JsonProperty("production_companies")
    List<ProductionCompany> productionCompanies;

    @JsonProperty("production_countries")
    List<ProductionCountry> productionCountries;

    @JsonProperty("release_date")
    Date releaseDate;

    @JsonProperty("status")
    String status;

    @JsonProperty("title")
    String title;


}

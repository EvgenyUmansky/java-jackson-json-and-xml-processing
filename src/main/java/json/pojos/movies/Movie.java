package json.pojos.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import json.topics.views.MovieViews;
import json.topics.custom.deserializers.MovieOverviewDeserializer;
import json.topics.custom.serializers.MovieOverviewSerializer;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
// @JsonSerialize(using = MovieSerializer.class)
// @JsonDeserialize(using = MovieDeserializer.class)
@JsonIgnoreProperties
public class Movie implements Serializable {

    // talk / overview JsonProperty with all its attributes
    @JsonView({MovieViews.Public.class})
    @JsonProperty(value = "id", access = Access.READ_WRITE)
    Integer movieId;

    @JsonView({MovieViews.Overview.class})
    @JsonProperty("budget")
    Integer budget;

    @JsonProperty("belongs_to_collection")
    Collection belongsToCollection;

    @JsonView({MovieViews.Genres.class})
    @JsonProperty("genres")
    List<Genre> genres;

    @JsonView({MovieViews.Overview.class})
    @JsonProperty("original_language")
    String originalLanguage;

    @JsonDeserialize(using = MovieOverviewDeserializer.class)
    @JsonSerialize(using = MovieOverviewSerializer.class)
    @JsonView({MovieViews.Overview.class})
    @JsonProperty("overview")
    String overview;

    @JsonView({MovieViews.Overview.class})
    @JsonProperty("popularity")
    Float popularity;

    @JsonView({MovieViews.ProductionCompanies.class})
    @JsonProperty("production_companies")
    List<ProductionCompany> productionCompanies;

    @JsonView({MovieViews.ProductionCountries.class})
    @JsonProperty("production_countries")
    List<ProductionCountry> productionCountries;

    @JsonView({MovieViews.Overview.class})
    @JsonProperty("release_date")
    Date releaseDate;

    @JsonView({MovieViews.Overview.class})
    @JsonProperty("status")
    String status;

    @JsonView({MovieViews.Overview.class})
    @JsonProperty("title")
    String title;

}

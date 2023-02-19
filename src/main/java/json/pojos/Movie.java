package json.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Movie implements Serializable {

    // talk / overview JsonProperty with all its attributes
    @JsonProperty(value= "id", access = Access.READ_WRITE)
    Integer movieId;

    Boolean adult;

    @JsonAlias(value = {"backdropPath", "backdrop_path"})
    String backdropPath;

    @JsonProperty("belongs_to_collection")
    Collection belongsToCollection;

    @JsonProperty("budget")
    Integer budget;

    @JsonProperty("genres")
    List<Genre> genres;

    @JsonProperty("homepage")
    String homepage;

    @JsonProperty("imdb_id")
    String imdbId;

    @JsonProperty("original_language")
    String originalLanguage;

    @JsonProperty("original_title")
    String originalTitle;

    @JsonProperty("overview")
    String overview;

    @JsonProperty("popularity")
    Float popularity;

    @JsonProperty("poster_path")
    String posterPath;

    @JsonProperty("production_companies")
    List<ProductionCompany> productionCompanies;

    @JsonProperty("production_countries")
    List<ProductionCountry> productionCountries;

    @JsonProperty("release_date")
    Date releaseDate;

    @JsonProperty("revenue")
    Integer revenue;

    @JsonProperty("runtime")
    Integer runtime;

    @JsonProperty("spoken_languages")
    List<Language> spokenLanguages;

    @JsonProperty("status")
    String status;

    @JsonProperty("tagline")
    String tagline;

    @JsonProperty("title")
    String title;

    @JsonProperty("video")
    Boolean video;

    @JsonProperty("vote_average")
    Float voteAverage;

    @JsonProperty("vote_count")
    Integer voteCountry;


}

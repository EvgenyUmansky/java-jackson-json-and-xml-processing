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

    Collection belongsToCollection;

    Integer budget;

    List<Genre> genres;

    String homepage;

    String imdbId;

    String originalLanguage;

    String originalTitle;

    String overview;

    Float popularity;

    String posterPath;

    List<ProductionCompany> productionCompanies;

    List<ProductionCountry> productionCountries;

    Date releaseDate;

    Integer revenue;

    Integer runTime;

    List<Language> spokenLanguages;

    String status;

    String tagline;

    String title;

    Boolean video;

    Float voteAverage;

    Integer voteCountry;


}

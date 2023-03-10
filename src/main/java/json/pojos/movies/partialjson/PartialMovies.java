package json.pojos.movies.partialjson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import json.topics.MovieViews;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@JsonIgnoreProperties
public class PartialMovies implements Serializable {
    @JsonView({MovieViews.Public.class})
    @JsonProperty("movies")
    List<PartialMovie> partialMovies;
}

package json.pojos.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import json.topics.views.MovieViews;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@JsonIgnoreProperties
public class Movies implements Serializable {
    @JsonView({MovieViews.Public.class})
    @JsonProperty("movies")
    List<Movie> movies;
}

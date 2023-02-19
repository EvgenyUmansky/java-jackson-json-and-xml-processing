package json.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Movies implements Serializable {
    @JsonProperty("movies")
    List<Movie> movies;
}

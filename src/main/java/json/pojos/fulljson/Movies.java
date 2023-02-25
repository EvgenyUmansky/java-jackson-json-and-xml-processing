package json.pojos.fulljson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties
public class Movies implements Serializable {
    @JsonProperty("movies")
    List<Movie> movies;
}

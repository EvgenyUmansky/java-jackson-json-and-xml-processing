package json.pojos.fulljson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class Collection implements Serializable {
    @JsonProperty("id")
    Integer collectionId;

    @JsonProperty("backdrop_path")
    String backdropPath;

    @JsonProperty("name")
    String collectionName;

    @JsonProperty("poster_path")
    String posterPath;
}

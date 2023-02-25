package json.pojos.fulljson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Genre {
    @JsonProperty("id")
    Integer genreId;

    @JsonProperty("name")
    String genreName;
}

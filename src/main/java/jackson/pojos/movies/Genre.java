package jackson.pojos.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties
public class Genre {
    @JsonProperty("id")
    Integer genreId;

    @JsonProperty("name")
    String genreName;
}

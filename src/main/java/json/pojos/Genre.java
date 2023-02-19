package json.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Genre
{
    @JsonProperty("id")
    Integer genreId;

    @JsonProperty("name")
    String genreName;
}

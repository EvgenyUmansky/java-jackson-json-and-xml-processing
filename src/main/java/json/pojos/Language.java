package json.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Language implements Serializable {
    @JsonProperty("iso_639_1")
    String languageCode;

    @JsonProperty("name")
    String languageName;
}

package json.pojos.fulljson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class Language implements Serializable {
    @JsonProperty("iso_639_1")
    String languageCode;

    @JsonProperty("name")
    String languageName;
}

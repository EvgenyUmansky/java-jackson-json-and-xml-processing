package json.pojos;

import lombok.Data;

import java.io.Serializable;

@Data
public class Language implements Serializable {
    String languageCode;

    String languageName;
}

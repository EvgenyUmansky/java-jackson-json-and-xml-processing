package json.pojos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductionCountry implements Serializable {
    String countryCode;

    String countryName;
}

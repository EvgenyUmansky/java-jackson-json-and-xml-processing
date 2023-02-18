package json.pojos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductionCompany implements Serializable {
    Integer productionCompaniesId;

    String logoPath;

    String productionCompaniesName;

    String originCountry;
}

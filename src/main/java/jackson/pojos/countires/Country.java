package jackson.pojos.countires;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jackson.topics.custom.deserializers.bidirectional.CountryCityListBidirectionalDeserializer;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "country_id")
public class Country implements Serializable {

    @JsonProperty("country_id")
    Integer countryId;

    @JsonProperty("country_name")
    String countryName;

    @JsonProperty("capital_name")
    String capitalName;

    @JsonDeserialize(using = CountryCityListBidirectionalDeserializer.class)
    @JsonBackReference(value="cities")
    @JsonProperty("cities")
    List<City> cities;

}

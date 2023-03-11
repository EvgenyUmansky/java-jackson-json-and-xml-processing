package jackson.pojos.countires;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "city_id")
public class City implements Serializable {

    @JsonProperty("city_id")
    Integer cityId;

    @JsonProperty("city_name")
    String cityName;

    //@JsonIgnore
    @JsonManagedReference(value="country")
    @JsonProperty("country")
    Country country;

}

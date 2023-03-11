package json.topics.custom.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import json.pojos.countires.City;
import json.pojos.countires.Country;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryCityListBidirectionalDeserializer extends JsonDeserializer<List<City>> {
    @Override
    public List<City> deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        List<City> cityList = new ArrayList<>();

        JsonNode cityListNode = jsonParser.readValueAsTree();

        if (cityListNode != null && cityListNode.isArray()) {
            for (JsonNode cityNode : cityListNode) {
                City city = new City();

                if (cityNode.get("city_id") != null) {
                    city.setCityId(cityNode.get("city_id").asInt());
                }

                if (cityNode.get("city_name") != null) {
                    city.setCityName(cityNode.get("city_name").asText());
                }

                JsonNode countryNode = cityNode.get("country");

                Country country = new Country();
                country.setCountryId(countryNode.get("country_id").asInt());
                country.setCountryName(countryNode.get("country_name").asText());
                country.setCapitalName(countryNode.get("capital_name").asText());

                city.setCountry(country);

                cityList.add(city);
            }
        }
        return cityList;
    }
}

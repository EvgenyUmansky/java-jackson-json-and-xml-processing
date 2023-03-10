package json.topics.custom.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * deserialize on overview field only
 */
public class MovieOverviewDeserializer extends JsonDeserializer<String> {
    // deserialize on field
    // JsonDeserializer<T> - T must be a class of the field
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.readValueAsTree();

        return node.asText().toUpperCase();
    }
}

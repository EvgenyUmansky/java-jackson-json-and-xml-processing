package json.topics;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import json.pojos.movies.Movie;
import json.pojos.movies.Movies;

import java.io.IOException;

// public class MovieDeserializer  extends JsonDeserializer<Movie> {
public class MovieDeserializer extends JsonDeserializer<String> {
    // deserialize on class
    // have to define all fields
//    @Override
//    public Movie deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        Movie movie = new Movie();
//        JsonNode node = jsonParser.readValueAsTree();
//        JsonNode overviewNode = node.get("overview");
//
//        if(overviewNode != null) {
//            movie.setOverview(overviewNode.asText().toUpperCase());
//        }
//
//        return movie;
//    }

    // deserialize on field
    // JsonDeserializer<T> - T must be a class of the field
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.readValueAsTree();

        return node.asText().toUpperCase();
    }
}

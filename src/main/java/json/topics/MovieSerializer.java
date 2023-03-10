package json.topics;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import json.pojos.movies.Movie;

import java.io.IOException;

public class MovieSerializer extends JsonSerializer<Movie> {
//public class MovieSerializer extends JsonSerializer<String> {
    // serialize on class
    // have to define all fields
    @Override
    public void serialize(Movie value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("overview", value.getOverview().toLowerCase());
        jsonGenerator.writeEndObject();
    }

    // Serialize on field
    // JsonDeserializer<T> - T must be a class of the field
//    @Override
//    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
//        jsonGenerator.writeObject(value.toLowerCase());
//    }
}

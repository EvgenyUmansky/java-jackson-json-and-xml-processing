package json.topics.serialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.pojos.movies.partialjson.PartialMovie;
import json.pojos.movies.partialjson.PartialMovies;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JacksonStreamingApiWithTreeModelSerialization {

    // Create and configure an ObjectMapper instance
    ObjectMapper objectMapper = new ObjectMapper();

    public void streamTreeModelSerializeMovies(PartialMovies partialMovies) throws IOException {

        // Create a factory which will be used for creating a JsonGenerator instance
        JsonFactory jsonFactory = new JsonFactory();

        List<PartialMovie> movies = partialMovies.getPartialMovies();

        // Create a JsonGenerator instance
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(new FileOutputStream("stream_tree_serialized_movies.json"))) {

            // Configure the JsonGenerator to pretty print the output
            jsonGenerator.useDefaultPrettyPrinter();

            // Write the start object token
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("movies");

            // Write the start array token
            jsonGenerator.writeStartArray();

            // Iterate over the contacts and write each contact as a JSON object
            for (PartialMovie movie : movies) {
                objectMapper.writeValue(jsonGenerator, movie);
            }

            // Write the end array token
            jsonGenerator.writeEndArray();

            // Write the end object token
            jsonGenerator.writeEndObject();
        }
    }
}
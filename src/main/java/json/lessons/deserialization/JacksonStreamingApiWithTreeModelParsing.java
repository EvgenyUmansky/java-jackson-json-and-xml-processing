package json.lessons.deserialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.pojos.movies.partialjson.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JacksonStreamingApiWithTreeModelParsing {
    public PartialMovies readStreamTreeModelMovies(String fileName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        PartialMovies partialMovies = new PartialMovies();
        List<PartialMovie> partialMovieList = new ArrayList<>();

        // Create a factory for creating a JsonParser instance
        JsonFactory jsonFactory = new JsonFactory();

        // Create a JsonParser instance
        try (JsonParser jsonParser = jsonFactory.createParser(AutoParsing.class.getResourceAsStream("/%s".formatted(fileName)))) {

            // Check the first token
            while (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                jsonParser.nextToken();
            }

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                // Read a contact instance using ObjectMapper and do something with it
                PartialMovie partialMovie = objectMapper.readValue(jsonParser, PartialMovie.class);
                partialMovieList.add(partialMovie);
            }

            partialMovies.setPartialMovies(partialMovieList);

        } catch (Exception e) {
            throw new Exception(e);
        }

        return partialMovies;
    }
}

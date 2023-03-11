package jackson.topics.deserialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.pojos.movies.Movie;
import jackson.pojos.movies.Movies;

import java.util.ArrayList;
import java.util.List;

public class JacksonStreamingApiWithTreeModelParsing {
    public Movies readStreamTreeModelMovies(String fileName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Movies movies = new Movies();
        List<Movie> movieList = new ArrayList<>();

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
                Movie movie = objectMapper.readValue(jsonParser, Movie.class);
                movieList.add(movie);
            }

            movies.setMovies(movieList);

        } catch (Exception e) {
            throw new Exception(e);
        }

        return movies;
    }
}

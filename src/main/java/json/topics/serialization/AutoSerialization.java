package json.topics.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.pojos.movies.Movies;

import java.io.IOException;

public class AutoSerialization {
    ObjectMapper objectMapper = new ObjectMapper();

    public String simpleSerialization(Movies movies) throws IOException {
        String serializedMovies = objectMapper.writeValueAsString(movies);

        return serializedMovies;
    }

    public String treeModelSerialization(Movies movies) throws IOException {

        JsonNode moviesPartialJsonNode =
                objectMapper.valueToTree(movies);

        return moviesPartialJsonNode.toString();
    }
}

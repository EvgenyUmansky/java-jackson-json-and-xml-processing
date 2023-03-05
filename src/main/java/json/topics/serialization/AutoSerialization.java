package json.topics.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.pojos.movies.partialjson.*;

import java.io.IOException;

public class AutoSerialization {
    ObjectMapper objectMapper = new ObjectMapper();

    public String simpleSerialization(PartialMovies partialMovies) throws IOException {
        String serializedMovies = objectMapper.writeValueAsString(partialMovies);

        return serializedMovies;
    }

    public String treeModelSerialization(PartialMovies partialMovies) throws IOException {

        JsonNode moviesPartialJsonNode =
                objectMapper.valueToTree(partialMovies);

        return moviesPartialJsonNode.toString();
    }
}

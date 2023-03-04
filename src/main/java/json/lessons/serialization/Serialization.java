package json.lessons.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.lessons.deserialization.AutoParsing;
import json.pojos.movies.partialjson.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Serialization {
    ObjectMapper objectMapper = new ObjectMapper();

    public String simpleSerialization(PartialMovies partialMovies) throws IOException {
        String serializedMovies = objectMapper.writeValueAsString(partialMovies);

        return serializedMovies;
    }

    public String treeModelSerialization(PartialMovies partialMovies) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode moviesPartialJsonNode =
                objectMapper.valueToTree(partialMovies);

        return moviesPartialJsonNode.toString();
    }
}

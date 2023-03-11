package jackson.topics.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jackson.pojos.movies.Movies;

import java.io.IOException;

public class AutoSerialization {
    ObjectMapper objectMapper = new ObjectMapper();

    public String simpleSerialization(Movies movies) throws IOException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Jackson will serialize the Date to a timestamp format by default

        String serializedMovies = objectMapper.writeValueAsString(movies);

        return serializedMovies;
    }

    public String treeModelSerialization(Movies movies) throws IOException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Jackson will serialize the Date to a timestamp format by default
       // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
       // objectMapper.setDateFormat(simpleDateFormat);

        JsonNode moviesPartialJsonNode =
                objectMapper.valueToTree(movies);

        return moviesPartialJsonNode.toString();
    }
}

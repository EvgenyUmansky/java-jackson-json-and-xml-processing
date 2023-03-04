package json.topics.deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.JsonProcessingUtils;
import json.pojos.movies.fulljson.Movies;
import json.pojos.movies.partialjson.PartialMovies;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j2
public class AutoParsing {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void readPartialJsonFromFileMap() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        // From File
        long start = System.currentTimeMillis();
        PartialMovies moviesJsonFromFile = readValueSerializePartialMoviesToPojo("all_movies.json");
        long end = System.currentTimeMillis();

        String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesJsonFromFile);
        // System.out.println(resultFromFile);

        System.out.printf("[UseObjectMapper] auto parsing with ObjectMapper readValue runtime: %d ms%n", end - start);
    }

    public void readPartialJsonFromFilePojo() throws Exception {

        // From File
        long start = System.currentTimeMillis();
        readValueSerializePartialMoviesToPojo("all_movies.json");
        long end = System.currentTimeMillis();

        System.out.printf("[UseObjectMapper] auto parsing with ObjectMapper readValue runtime: %d ms%n", end - start);
    }

    public void readFromString() throws Exception {
        String movies = JsonProcessingUtils.mergeJsonToString(
                Objects.requireNonNull(AutoParsing.class.getResource("/separate_movie_jsons"))
                        .getFile()
                        .replaceFirst("/", ""), // replace /C:/ to C:/
                "movies");

        // --------------- Read JSON with Object Mapper ---------------
        ObjectMapper objectMapper = new ObjectMapper();

        // From string
        Movies moviesJsonFromString = objectMapper.readValue(movies, Movies.class);

        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesJsonFromString);
        System.out.println(result);
    }

    public Movies readValueSerializeFullMovies(String fileName) throws IOException {

        return objectMapper.readValue(
                AutoParsing.class.getResourceAsStream("/%s".formatted(fileName)),
                Movies.class);
    }

    public Map readValueSerializePartialMoviesToRawMap(String fileName) throws IOException {

        Map parsedMovies = objectMapper.readValue(
                AutoParsing.class.getResourceAsStream("/%s".formatted(fileName)),
                Map.class);

        // String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parsedMovies);
        // System.out.println(resultFromFile);

        return parsedMovies;
    }

    public HashMap<String, Object> readValueSerializePartialMoviesToTypeReferenceMap(String fileName) throws IOException {

        TypeReference<HashMap<String, Object>> typeRef
                = new TypeReference<>() {
        };

        HashMap<String, Object> parsedMovies = objectMapper.readValue(
                AutoParsing.class.getResourceAsStream("/%s".formatted(fileName)),
                typeRef);

        // String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesJsonFromFile);
        // System.out.println(parsedMovies);

        return parsedMovies;
    }

    public PartialMovies readValueSerializePartialMoviesToPojo(String fileName) throws IOException {

        PartialMovies partialMovies = objectMapper.readValue(
                AutoParsing.class.getResourceAsStream("/%s".formatted(fileName)),
                PartialMovies.class);

        // String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesJsonFromFile);
        // System.out.println(partialMovies);

        return partialMovies;
    }

    public void addValueToJson(String fileName) throws IOException {

    }
}

package json.lessons;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.JsonProcessingUtils;
import json.pojos.fulljson.Movies;
import json.pojos.partialjson.PartialMovies;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Objects;

@Log4j2
public class UseObjectMapper {

    public static void readFromFile() throws Exception {
        JsonProcessingUtils.mergeJsonToFile(
                Objects.requireNonNull(UseObjectMapper.class.getResource("/separate_movie_jsons"))
                        .getFile()
                        .replaceFirst("/", ""), // replace /C:/ to C:/
                Objects.requireNonNull(UseObjectMapper.class.getResource(""))
                        .getFile()
                        .replaceFirst("/", ""), // replace /C:/ to C:/
                "all_movies.json",
                "movies");

        ObjectMapper objectMapper = new ObjectMapper();

        // From File
        Movies moviesJsonFromFile = readValueSerializeFullMovies("all_movies.json");

        String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesJsonFromFile);
        System.out.println(resultFromFile);
    }

    public static void readPartialJsonFromFile() throws Exception {
        JsonProcessingUtils.mergePartialJsonToFile(
                Objects.requireNonNull(UseObjectMapper.class.getResource("/separate_movie_jsons"))
                        .getFile()
                        .replaceFirst("/", ""), // replace /C:/ to C:/
                Objects.requireNonNull(UseObjectMapper.class.getResource(""))
                        .getFile()
                        .replaceFirst("/", ""), // replace /C:/ to C:/
                "all_movies.json",
                "movies");

        ObjectMapper objectMapper = new ObjectMapper();

        // From File
        long start = System.currentTimeMillis();
        PartialMovies moviesJsonFromFile = readValueSerializePartialMovies("all_movies.json");
        long end = System.currentTimeMillis();

        String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesJsonFromFile);
        // System.out.println(resultFromFile);

        System.out.printf("[UseObjectMapper] auto parsing with ObjectMapper readValue runtime: %d ms%n", end - start);
    }

    public static void readFromString() throws Exception {
        String movies = JsonProcessingUtils.mergeJsonToString(
                Objects.requireNonNull(UseObjectMapper.class.getResource("/separate_movie_jsons"))
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

    public static Movies readValueSerializeFullMovies(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(
                UseObjectMapper.class.getResourceAsStream("/%s".formatted(fileName)),
                Movies.class);
    }

    public static PartialMovies readValueSerializePartialMovies(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(
                UseObjectMapper.class.getResourceAsStream("/%s".formatted(fileName)),
                PartialMovies.class);
    }
}

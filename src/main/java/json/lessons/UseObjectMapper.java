package json.lessons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.JsonProcessingUtils;
import json.pojos.Movies;

import java.nio.file.Paths;

public class UseObjectMapper {

    public static void readFromFile() throws Exception {
        JsonProcessingUtils.mergeJsonToFile(
                "C:\\Users\\eugum\\Desktop\\JavaCourses\\JsonXml\\java-json-xml-processing\\src\\main\\resources\\separate_movie_jsons",
                "C:\\Users\\eugum\\Desktop\\JavaCourses\\JsonXml\\java-json-xml-processing\\src\\main\\resources",
                "all_movies.json",
                "movies");

        ObjectMapper objectMapper = new ObjectMapper();

        // From File
        Movies moviesJsonFromFile = objectMapper.readValue(Paths
                        .get("C:\\Users\\eugum\\Desktop\\JavaCourses\\JsonXml\\java-json-xml-processing\\src\\main\\resources/all_movies.json")
                        .toFile(),
                Movies.class);

        String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesJsonFromFile);
        System.out.println(resultFromFile);
    }

    public static void readFromString() throws Exception {
        String movies = JsonProcessingUtils.mergeJsonToString("C:\\Users\\eugum\\Desktop\\JavaCourses\\JsonXml\\java-json-xml-processing\\src\\main\\resources\\separate_movie_jsons",
                "movies");

        // --------------- Read JSON with Object Mapper ---------------
        ObjectMapper objectMapper = new ObjectMapper();

        // From string
        Movies moviesJsonFromString = objectMapper.readValue(movies, Movies.class);

        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesJsonFromString);
        System.out.println(result);
    }
}

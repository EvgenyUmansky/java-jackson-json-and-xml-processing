package jackson;

import java.util.Objects;

public class JsonProcessingMain {
    public static void main(String[] args) throws Exception {
        // --------------- Read JSON with Object Mapper ---------------

        // From File
        JsonProcessingUtils.mergePartialJsonToFile(
                Objects.requireNonNull(JsonProcessingMain.class.getResource("/separate_movie_jsons"))
                        .getFile()
                        .replaceFirst("/", ""), // replace /C:/ to C:/
                Objects.requireNonNull(JsonProcessingMain.class.getResource(""))
                        .getFile()
                        .replaceFirst("/", ""), // replace /C:/ to C:/
                "all_movies.json",
                "movies");

    }
}
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static json.lessons.ParallelUseJsonNode.readTreeParallelSerializePartialMovies;
import static json.lessons.UseJsonNode.readTreeSequentialSerializePartialMovies;
import static json.lessons.UseObjectMapper.readValueSerializePartialMovies;

public class JacksonTest {
    @Test
    public void runtimeTest() throws IOException, ParseException {

        // readValue()
        long start = System.currentTimeMillis();
        readValueSerializePartialMovies("all_movies.json");
        long end = System.currentTimeMillis();

        System.out.printf("[UseObjectMapper] auto parsing with ObjectMapper readValue runtime: %d ms%n",
                end - start); // 603 ms

        // sequential readTree()
        start = System.currentTimeMillis();
        readTreeSequentialSerializePartialMovies("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[UseJsonNode] manual sequential parsing with ObjectMapper readTree runtime: %d ms%n",
                end - start); // 26 ms

        // parallel readTree()
        start = System.currentTimeMillis();
        readTreeParallelSerializePartialMovies("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[UseJsonNode] manual stream parallel parsing with ObjectMapper readTree runtime: %d ms%n",
                end - start); // 16 ms
    }
}

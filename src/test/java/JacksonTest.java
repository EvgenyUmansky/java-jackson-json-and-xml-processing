import json.lessons.ParallelUseJsonNode;
import json.lessons.UseJsonNode;
import json.lessons.UseObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

public class JacksonTest {
    @Test
    public void runtimeTest() throws IOException, ParseException {
        long start;
        long end;
        UseObjectMapper useObjectMapper = new UseObjectMapper();
        UseJsonNode useJsonNode = new UseJsonNode();
        ParallelUseJsonNode parallelUseJsonNode = new ParallelUseJsonNode();

        // readValue(): raw map
        start = System.currentTimeMillis();
        useObjectMapper.readValueSerializePartialMoviesToRawMap("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[UseObjectMapper] auto parsing raw map with ObjectMapper readValue runtime: %d ms%n",
                end - start); // 34 ms

        // readValue(): type reference map
        start = System.currentTimeMillis();
        useObjectMapper.readValueSerializePartialMoviesToTypeReferenceMap("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[UseObjectMapper] auto parsing type reference map with ObjectMapper readValue runtime: %d ms%n",
                end - start); // 6 ms

        // readValue(): POJO
        start = System.currentTimeMillis();
        useObjectMapper.readValueSerializePartialMoviesToPojo("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[UseObjectMapper] auto parsing POJO with ObjectMapper readValue runtime: %d ms%n",
                end - start); // 51 ms



        // sequential readTree()
        start = System.currentTimeMillis();
        useJsonNode.readTreeSequentialSerializePartialMovies("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[UseJsonNode] manual sequential parsing with ObjectMapper readTree runtime: %d ms%n",
                end - start); // 18 ms

        // parallel readTree()
        start = System.currentTimeMillis();
        parallelUseJsonNode.readTreeParallelSerializePartialMovies("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[UseJsonNode] manual stream parallel parsing with ObjectMapper readTree runtime: %d ms%n",
                end - start); // 10 ms
    }
}

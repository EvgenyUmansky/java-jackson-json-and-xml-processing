import json.lessons.deserialization.*;
import json.lessons.serialization.Serialization;
import json.pojos.movies.partialjson.PartialMovies;
import org.junit.jupiter.api.Test;

public class JacksonTest {
    @Test
    public void runtimeTest() throws Exception {
        long start;
        long end;
        AutoParsing autoParsing = new AutoParsing();
        ManualTreeModelParsing manualTreeModelParsing = new ManualTreeModelParsing();
        ManualTreeModelParallelParsing manualTreeModelParallelParsing = new ManualTreeModelParallelParsing();
        JacksonStreamingApiParsing jacksonStreamingApiParsing = new JacksonStreamingApiParsing();
        JacksonStreamingApiWithTreeModelParsing JacksonStreamingApiWithTreeModelParsing = new JacksonStreamingApiWithTreeModelParsing();

        // ######## Deserialization / Unmarshalling / Parsing / JSON -> Java POJO ########
        System.out.println("######## Deserialization / Unmarshalling / Parsing / JSON -> Java POJO ########");

        // readValue(): raw map
        start = System.currentTimeMillis();
        autoParsing.readValueSerializePartialMoviesToRawMap("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[AutoParsing] Deserialization auto parsing raw map with ObjectMapper readValue runtime: %d ms%n",
                end - start); // 34 ms

        // readValue(): type reference map
        start = System.currentTimeMillis();
        autoParsing.readValueSerializePartialMoviesToTypeReferenceMap("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[AutoParsing] Deserialization auto parsing type reference map with ObjectMapper readValue runtime: %d ms%n",
                end - start); // 6 ms

        // readValue(): POJO
        start = System.currentTimeMillis();
        autoParsing.readValueSerializePartialMoviesToPojo("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[AutoParsing] Deserialization auto parsing POJO with ObjectMapper readValue runtime: %d ms%n",
                end - start); // 51 ms

        // sequential readTree()
        start = System.currentTimeMillis();
        manualTreeModelParsing.readTreeSequentialSerializePartialMovies("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[ManualParsing] Deserialization manual sequential parsing with ObjectMapper readTree runtime: %d ms%n",
                end - start); // 18 ms

        // parallel readTree()
        start = System.currentTimeMillis();
        manualTreeModelParallelParsing.readTreeParallelSerializePartialMovies("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[ManualParallelParsing] Deserialization manual stream parallel parsing with ObjectMapper readTree runtime: %d ms%n",
                end - start); // 10 ms

        // Jackson Streaming API: manual parsing
        start = System.currentTimeMillis();
        jacksonStreamingApiParsing.readStreamMovies("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[JacksonStreamingApiParsing] Deserialization Jackson Streaming API manual parsing with JsonParser runtime: %d ms%n",
                end - start); // 8 ms

        // Jackson Streaming API: Tree Model parsing
        start = System.currentTimeMillis();
        JacksonStreamingApiWithTreeModelParsing.readStreamTreeModelMovies("all_movies.json");
        end = System.currentTimeMillis();

        System.out.printf("[JacksonStreamingApiWithTreeModelParsing] Deserialization Jackson Streaming API combining " +
                        "ObjectMapper Tree Model parsing runtime: %d ms%n",
                end - start); // 14 ms

        // ######## Serialization / Marshalling / Java POJO -> JSON ########
        System.out.println();
        System.out.println("######## Serialization / Marshalling / Java POJO -> JSON ########");

        // Simple Serialization
        Serialization serialization = new Serialization();

        PartialMovies partialMovies = manualTreeModelParallelParsing.readTreeParallelSerializePartialMovies("all_movies.json");

        start = System.currentTimeMillis();
        serialization.simpleSerialization(partialMovies);
        end = System.currentTimeMillis();

        System.out.printf("[Serialization] Serialization auto parsing ObjectMapper writeValueAsString runtime: %d ms%n",
                end - start);

        // Tree Model Serialization
        start = System.currentTimeMillis();
        serialization.treeModelSerialization(partialMovies);
        end = System.currentTimeMillis();

        System.out.printf("[Serialization] Serialization auto parsing ObjectMapper writeValueAsString runtime: %d ms%n",
                end - start);
    }
}

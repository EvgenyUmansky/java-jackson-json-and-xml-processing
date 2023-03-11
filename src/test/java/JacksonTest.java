import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.pojos.countires.City;
import jackson.pojos.countires.Country;
import jackson.pojos.movies.Movie;
import jackson.pojos.movies.Movies;
import jackson.topics.compare.MovieComparator;
import jackson.topics.views.MovieViews;
import jackson.topics.deserialization.*;
import jackson.topics.serialization.AutoSerialization;
import jackson.topics.serialization.JacksonStreamingApiSerialization;
import jackson.topics.serialization.JacksonStreamingApiWithTreeModelSerialization;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        JacksonStreamingApiSerialization jacksonStreamingApiSerialization = new JacksonStreamingApiSerialization();
        JacksonStreamingApiWithTreeModelSerialization jacksonStreamingApiWithTreeModelSerialization = new JacksonStreamingApiWithTreeModelSerialization();

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

        // ######## AutoSerialization / Marshalling / Java POJO -> JSON ########
        System.out.println();
        System.out.println("######## AutoSerialization / Marshalling / Java POJO -> JSON ########");

        // Simple AutoSerialization
        AutoSerialization autoSerialization = new AutoSerialization();

        Movies movies = manualTreeModelParallelParsing.readTreeParallelSerializePartialMovies("all_movies.json");

        start = System.currentTimeMillis();
        autoSerialization.simpleSerialization(movies);
        end = System.currentTimeMillis();

        System.out.printf("[AutoSerialization] AutoSerialization auto parsing ObjectMapper writeValueAsString runtime: %d ms%n",
                end - start); // 58 ms

        // Tree Model AutoSerialization
        start = System.currentTimeMillis();
        autoSerialization.treeModelSerialization(movies);
        end = System.currentTimeMillis();

        System.out.printf("[AutoSerialization] AutoSerialization tree model parsing ObjectMapper writeValueAsString runtime: %d ms%n",
                end - start); // 44 ms

        // Jackson Streaming API Manual Serialization
        start = System.currentTimeMillis();
        jacksonStreamingApiSerialization.streamSerializeMovies(movies);
        end = System.currentTimeMillis();

        System.out.printf("[JacksonStreamingApiSerialization] Jackson Streaming API manual serialization runtime: %d ms%n",
                end - start); // 15 ms

        // Jackson Streaming API Manual Serialization
        start = System.currentTimeMillis();
        jacksonStreamingApiWithTreeModelSerialization.streamTreeModelSerializeMovies(movies);
        end = System.currentTimeMillis();

        System.out.printf("[JacksonStreamingApiWithTreeModelSerialization] Jackson Streaming API tree model serialization runtime: %d ms%n",
                end - start); // 19 ms
    }

    @Test
    public void viewTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//        DeserializationConfig deserializationConfig = objectMapper.getDeserializationConfig().with(MapperFeature.DEFAULT_VIEW_INCLUSION);
//        objectMapper.setConfig(deserializationConfig);

        // Jackson Streaming API: Tree Model parsing
        Movies movies = objectMapper.readerWithView(MovieViews.Public.class).readValue(
                JacksonTest.class.getResourceAsStream("/%s".formatted("all_movies.json")),
                Movies.class);

        String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(movies);
        System.out.println(resultFromFile);
    }

    @Test
    public void customDeserializerTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Jackson Streaming API: Tree Model parsing
        Movies movies = objectMapper.readValue(
                JacksonTest.class.getResourceAsStream("/%s".formatted("all_movies.json")),
                Movies.class);

        String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(movies);
        System.out.println(resultFromFile);
    }

    @Test
    public void customSerializerTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Jackson Streaming API: Tree Model parsing
        Movies movies = objectMapper.readValue(
                JacksonTest.class.getResourceAsStream("/%s".formatted("all_movies.json")),
                Movies.class);


        JsonNode moviesPartialJsonNode =
                objectMapper.valueToTree(movies);

        System.out.println(moviesPartialJsonNode.toString());
    }

    @Test
    public void bidirectionalSerializerTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

//        Country country = objectMapper.readValue(
//                JacksonTest.class.getResourceAsStream("/%s".formatted("bidirectional_country.json")),
//                Country.class);

        Country country = new Country();
        country.setCountryId(1);
        country.setCountryName("Ireland");
        country.setCapitalName("Dublin");

        City city = new City();
        city.setCityId(2);
        city.setCityName("Waterford");
        city.setCountry(country);

        List<City> cities = new ArrayList<>();
        cities.add(city);

        country.setCities(cities);

        System.out.println(objectMapper.writeValueAsString(city));
    }

    @Test
    public void bidirectionalDeserializerTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Country country = objectMapper.readValue(
                JacksonTest.class.getResourceAsStream("/%s".formatted("bidirectional_country.json")),
                Country.class);

        System.out.println(country.toString());
    }

    @Test
    public void compareJsonsTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Movie movie_1 = objectMapper.readValue(
                JacksonTest.class.getResourceAsStream("/%s".formatted("compare_movie_1.json")),
                Movie.class);

        Movie movie_2 = objectMapper.readValue(
                JacksonTest.class.getResourceAsStream("/%s".formatted("compare_movie_2.json")),
                Movie.class);

        // assertEquals(movie_1, movie_2);
        if(movie_1.equals(movie_2)){
            System.out.println("equals");
        }

        if(!movie_1.equals(movie_2)){
            System.out.println("not equals");
        }
    }

    @Test
    public void compareJsonsWithComparatorTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode movie_1 = objectMapper.readTree(
                JacksonTest.class.getResourceAsStream("/%s".formatted("compare_movie_1.json")));

        JsonNode movie_2 = objectMapper.readTree(
                JacksonTest.class.getResourceAsStream("/%s".formatted("compare_movie_2.json")));

        MovieComparator movieComparator = new MovieComparator();

        if(movie_1.equals(movieComparator, movie_2)){
            System.out.println("equals");
        }

        if(!movie_1.equals(movieComparator, movie_2)){
            System.out.println("not equals");
        }
    }
}

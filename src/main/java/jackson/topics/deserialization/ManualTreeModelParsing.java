package jackson.topics.deserialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.JsonProcessingUtils;
import jackson.pojos.movies.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
public class ManualTreeModelParsing {
    public void parseJson() throws Exception {
        JsonProcessingUtils.mergePartialJsonToFile(
                Objects.requireNonNull(AutoParsing.class.getResource("/separate_movie_jsons"))
                        .getFile()
                        .replaceFirst("/", ""), // replace /C:/ to C:/
                Objects.requireNonNull(AutoParsing.class.getResource(""))
                        .getFile()
                        .replaceFirst("/", ""), // replace /C:/ to C:/
                "all_movies.json",
                "movies");

        // You may choose readTree when you do not know exact type of the Object
        // or want to preprocess a field before adding it to POJO
        long start = System.currentTimeMillis();
        Movies movies = readTreeSequentialSerializePartialMovies("all_movies.json");
        long end = System.currentTimeMillis();

        System.out.printf("[UseJsonNode] manual sequential parsing with ObjectMapper readTree runtime: %d ms%n", end - start);
    }

    public Movies readTreeSequentialSerializePartialMovies(String fileName) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode moviesJsonNode =
                objectMapper.readTree(AutoParsing.class.getResourceAsStream("/%s".formatted(fileName))
                );

        // create main POJO
        Movies movies = new Movies();

        JsonNode moviesNode = moviesJsonNode.get("movies");

        if (!moviesNode.isNull() && moviesNode.isArray()) {
            List<Movie> movieList = new ArrayList<>();

            for (JsonNode movieNode : moviesNode) {
                Movie movie = new Movie();

                // ############ FLAT FIELDS ############
                if (!movieNode.get("id").isNull()) {
                    movie.setMovieId(movieNode.get("id").asInt());
                }

                if (!movieNode.get("budget").isNull()) {
                    movie.setBudget(movieNode.get("budget").asInt() / 1000000); // example for preprocessing
                }

                if (!movieNode.get("original_language").isNull()) {
                    movie.setOriginalLanguage(movieNode.get("original_language").asText());
                }

                if (!movieNode.get("overview").isNull()) {
                    movie.setOverview(movieNode.get("overview").asText());
                }

                if (!movieNode.get("popularity").isNull()) {
                    movie.setPopularity((float) movieNode.get("popularity").asDouble());
                }

                if (!movieNode.get("release_date").isNull()) {
                    movie.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(movieNode.get("release_date").asText()));
                }

                if (!movieNode.get("status").isNull()) {
                    movie.setStatus(movieNode.get("status").asText());
                }

                if (!movieNode.get("title").isNull()) {
                    movie.setTitle(movieNode.get("title").asText());
                }

                // ############ GENRES ############
                JsonNode genreListNode = movieNode.get("genres");

                if (!genreListNode.isNull() && genreListNode.isArray()) {
                    List<Genre> genreList = new ArrayList<>();

                    for (JsonNode genreNode : genreListNode) {
                        Genre genre = new Genre();

                        if (!genreNode.get("id").isNull()) {
                            genre.setGenreId(genreNode.get("id").asInt());
                        }

                        if (!genreNode.get("name").isNull()) {
                            genre.setGenreName(genreNode.get("name").asText());
                        }

                        genreList.add(genre);
                    }

                    movie.setGenres(genreList);
                }

                // OneToOne relationship
                // ############ COLLECTION ############
                JsonNode collectionNode = movieNode.get("belongs_to_collection");

                if (collectionNode != null && !collectionNode.isNull()) {
                    Collection collection = new Collection();

                    if (!collectionNode.get("id").isNull()) {
                        collection.setCollectionId(collectionNode.get("id").asInt());
                    }

                    if (!collectionNode.get("name").isNull()) {
                        collection.setCollectionName(collectionNode.get("name").asText());
                    }

                    if (!collectionNode.get("id").isNull()) {
                        collection.setPosterPath(collectionNode.get("poster_path").asText());
                    }

                    if (!collectionNode.get("id").isNull()) {
                        collection.setBackdropPath(collectionNode.get("backdrop_path").asText());
                    }

                    movie.setBelongsToCollection(collection);
                }

                // OneToMany relationship
                // ############ PRODUCTION COMPANIES ############
                JsonNode productionCompanyListNode = movieNode.get("production_companies");

                if (!productionCompanyListNode.isNull() && productionCompanyListNode.isArray()) {
                    List<ProductionCompany> productionCompanyList = new ArrayList<>();

                    for (JsonNode productionCompanyNode : productionCompanyListNode) {
                        ProductionCompany productionCompany = new ProductionCompany();

                        if (!productionCompanyNode.get("id").isNull()) {
                            productionCompany.setProductionCompanyId(productionCompanyNode.get("id").asInt());
                        }

                        if (!productionCompanyNode.get("name").isNull()) {
                            productionCompany.setProductionCompanyName(productionCompanyNode.get("name").asText());
                        }

                        // can be null
                        if (!productionCompanyNode.get("logo_path").isNull()) {
                            productionCompany.setLogoPath(productionCompanyNode.get("logo_path").asText());
                        }

                        if (!productionCompanyNode.get("origin_country").isNull()) {
                            productionCompany.setOriginCountry(productionCompanyNode.get("origin_country").asText());
                        }

                        productionCompanyList.add(productionCompany);
                    }

                    movie.setProductionCompanies(productionCompanyList);
                }

                // ############ PRODUCTION MOVIES ############
                JsonNode productionCountryListNode = movieNode.get("production_countries");

                if (!productionCountryListNode.isNull() && productionCountryListNode.isArray()) {
                    List<ProductionCountry> productionCountryList = new ArrayList<>();

                    for (JsonNode productionCountryNode : productionCountryListNode) {
                        ProductionCountry productionCountry = new ProductionCountry();

                        if (!productionCountryNode.get("iso_3166_1").isNull()) {
                            productionCountry.setCountryCode(productionCountryNode.get("iso_3166_1").asText());
                        }

                        if (!productionCountryNode.get("name").isNull()) {
                            productionCountry.setCountryName(productionCountryNode.get("name").asText());
                        }

                        productionCountryList.add(productionCountry);
                    }

                    movie.setProductionCountries(productionCountryList);
                }

                movieList.add(movie);
            }

            movies.setMovies(movieList);
        }

        // String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesJsonNode);
        // System.out.println(resultFromFile);

        return movies;
    }
}

package json.lessons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.pojos.partialjson.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.StreamSupport;

@Log4j2
public class ParallelUseJsonNode {
    public void parseJson() throws Exception {
        // You may choose readTree when you do not know exact type of the Object
        // or want to preprocess a field before adding it to POJO
        long start = System.currentTimeMillis();
        PartialMovies partialMovies = readTreeParallelSerializePartialMovies("all_movies.json");
        long end = System.currentTimeMillis();

        System.out.printf("[UseJsonNode] manual stream parallel parsing with ObjectMapper readTree runtime: %d ms%n",
                end - start);
    }

    public PartialMovies readTreeParallelSerializePartialMovies(String fileName) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode moviesPartialJsonNode =
                objectMapper.readTree(UseObjectMapper.class.getResourceAsStream("/%s".formatted(fileName))
                );

        // create main POJO
        PartialMovies partialMovies = new PartialMovies();

        JsonNode moviesNode = moviesPartialJsonNode.get("movies");

        if (!moviesNode.isNull() && moviesNode.isArray()) {
            List<PartialMovie> movies = new CopyOnWriteArrayList<>();

            StreamSupport.stream(moviesNode.spliterator(), true).forEach(movieNode -> {
                PartialMovie movie = new PartialMovie();

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
                    try {
                        movie.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(movieNode.get("release_date").asText()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
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
                    List<Genre> genreList = new CopyOnWriteArrayList<>();

                    StreamSupport.stream(genreListNode.spliterator(), true).forEach(genreNode -> {
                        Genre genre = new Genre();

                        if (!genreNode.get("id").isNull()) {
                            genre.setGenreId(genreNode.get("id").asInt());
                        }

                        if (!genreNode.get("name").isNull()) {
                            genre.setGenreName(genreNode.get("name").asText());
                        }

                        genreList.add(genre);
                    });

                    movie.setGenres(genreList);
                }

                // ############ PRODUCTION COMPANIES ############
                JsonNode productionCompanyListNode = movieNode.get("production_companies");

                if (!productionCompanyListNode.isNull() && productionCompanyListNode.isArray()) {
                    List<ProductionCompany> productionCompanyList = new CopyOnWriteArrayList<>();

                    StreamSupport.stream(productionCompanyListNode.spliterator(), true)
                            .forEach(productionCompanyNode -> {
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
                            });

                    movie.setProductionCompanies(productionCompanyList);
                }

                // ############ PRODUCTION MOVIES ############
                JsonNode productionCountryListNode = movieNode.get("production_countries");

                if (!productionCountryListNode.isNull() && productionCountryListNode.isArray()) {
                    List<ProductionCountry> productionCountryList = new CopyOnWriteArrayList<>();

                    StreamSupport.stream(productionCountryListNode.spliterator(), true)
                            .forEach(productionCountryNode -> {
                                ProductionCountry productionCountry = new ProductionCountry();

                                if (!productionCountryNode.get("iso_3166_1").isNull()) {
                                    productionCountry.setCountryCode(productionCountryNode.get("iso_3166_1").asText());
                                }

                                if (!productionCountryNode.get("name").isNull()) {
                                    productionCountry.setCountryName(productionCountryNode.get("name").asText());
                                }

                                productionCountryList.add(productionCountry);
                            });

                    movie.setProductionCountries(productionCountryList);
                }

                movies.add(movie);
            });

            partialMovies.setPartialMovies(movies);
        }

        // String resultFromFile = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(moviesPartialJsonNode);
        // System.out.println(resultFromFile);

        return partialMovies;
    }
}

package jackson.topics.serialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import jackson.pojos.movies.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JacksonStreamingApiSerialization {

    public void streamSerializeMovies(Movies partialMovies) throws IOException {

        // Create a factory which will be used for creating a JsonGenerator instance
        JsonFactory jsonFactory = new JsonFactory();

        List<Movie> movies = partialMovies.getMovies();

        // Create a JsonGenerator instance
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(new FileOutputStream("stream_serialized_movies.json"))) {

            // Configure the JsonGenerator to pretty print the output
            jsonGenerator.useDefaultPrettyPrinter();

            // Write the start object token
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("movies");

            // Write the start array token
            jsonGenerator.writeStartArray();

            // Iterate over the contacts and write each contact as a JSON object
            for (Movie movie : movies) {
                // Write the start object token
                jsonGenerator.writeStartObject();

                jsonGenerator.writeNumberField("id", movie.getMovieId());
                jsonGenerator.writeNumberField("budget", movie.getBudget());
                jsonGenerator.writeStringField("original_language", movie.getOriginalLanguage());
                jsonGenerator.writeStringField("overview", movie.getOverview());
                jsonGenerator.writeStringField("release_date", movie.getReleaseDate().toString());
                jsonGenerator.writeStringField("release_date", movie.getReleaseDate().toString());
                jsonGenerator.writeStringField("status", movie.getStatus());
                jsonGenerator.writeStringField("title", movie.getTitle());
                jsonGenerator.writeFieldName("genres");
                writeGenres(jsonGenerator, movie.getGenres());
                jsonGenerator.writeFieldName("production_companies");
                writeProductionCompanies(jsonGenerator, movie.getProductionCompanies());
                jsonGenerator.writeFieldName("production_countries");
                writeProductionCountries(jsonGenerator, movie.getProductionCountries());

                // Write the end object token
                jsonGenerator.writeEndObject();
            }

            // Write the end array token
            jsonGenerator.writeEndArray();

            // Write the end object token
            jsonGenerator.writeEndObject();
        }
    }

    private void writeGenres(JsonGenerator jsonGenerator, List<Genre> genreList) throws IOException {
        // Write the start array token
        jsonGenerator.writeStartArray();

        // Iterate over the emails and write each emails as a string
        for (Genre genre : genreList) {
            // Write the start object token
            jsonGenerator.writeStartObject();

            jsonGenerator.writeNumberField("id", genre.getGenreId());
            jsonGenerator.writeStringField("name", genre.getGenreName());

            // Write the end object token
            jsonGenerator.writeEndObject();
        }

        // Write the end array token
        jsonGenerator.writeEndArray();
    }

    private void writeProductionCompanies(JsonGenerator jsonGenerator, List<ProductionCompany> productionCompanyList) throws IOException {
        // Write the start array token
        jsonGenerator.writeStartArray();

        // Iterate over the emails and write each emails as a string
        for (ProductionCompany productionCompany : productionCompanyList) {
            // Write the start object token
            jsonGenerator.writeStartObject();

            jsonGenerator.writeNumberField("id", productionCompany.getProductionCompanyId());
            jsonGenerator.writeStringField("name", productionCompany.getProductionCompanyName());
            jsonGenerator.writeStringField("logo_path", productionCompany.getLogoPath());
            jsonGenerator.writeStringField("origin_country", productionCompany.getOriginCountry());

            // Write the end object token
            jsonGenerator.writeEndObject();
        }

        // Write the end array token
        jsonGenerator.writeEndArray();
    }

    private void writeProductionCountries(JsonGenerator jsonGenerator, List<ProductionCountry> productionCountryList) throws IOException {
        // Write the start array token
        jsonGenerator.writeStartArray();

        // Iterate over the emails and write each emails as a string
        for (ProductionCountry productionCountry : productionCountryList) {
            // Write the start object token
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("iso_3166_1", productionCountry.getCountryCode());
            jsonGenerator.writeStringField("name", productionCountry.getCountryName());

            // Write the end object token
            jsonGenerator.writeEndObject();
        }

        // Write the end array token
        jsonGenerator.writeEndArray();
    }
}

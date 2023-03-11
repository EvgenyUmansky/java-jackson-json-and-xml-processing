package jackson.topics.custom.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import jackson.pojos.movies.Genre;
import jackson.pojos.movies.Movie;
import jackson.pojos.movies.ProductionCompany;
import jackson.pojos.movies.ProductionCountry;

import java.io.IOException;
import java.util.List;

/**
 * serialize Movie class
 */
public class MovieSerializer extends JsonSerializer<Movie> {

    // serialize on class
    // have to define all fields
    @Override
    public void serialize(Movie value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
        // Write the start object token
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", value.getMovieId());
        jsonGenerator.writeNumberField("budget", value.getBudget());
        jsonGenerator.writeStringField("original_language", value.getOriginalLanguage());
        jsonGenerator.writeStringField("overview", value.getOverview().toLowerCase()); // custom serialization example
        jsonGenerator.writeStringField("release_date", value.getReleaseDate().toString());
        jsonGenerator.writeStringField("release_date", value.getReleaseDate().toString());
        jsonGenerator.writeStringField("status", value.getStatus());
        jsonGenerator.writeStringField("title", value.getTitle());
        jsonGenerator.writeFieldName("genres");
        writeGenres(jsonGenerator, value.getGenres());
        jsonGenerator.writeFieldName("production_companies");
        writeProductionCompanies(jsonGenerator, value.getProductionCompanies());
        jsonGenerator.writeFieldName("production_countries");
        writeProductionCountries(jsonGenerator, value.getProductionCountries());

        // Write the end object token
        jsonGenerator.writeEndObject();
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

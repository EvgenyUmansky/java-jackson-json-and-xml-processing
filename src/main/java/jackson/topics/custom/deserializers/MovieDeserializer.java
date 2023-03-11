package jackson.topics.custom.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import jackson.pojos.movies.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * deserialize Movie class
 */
public class MovieDeserializer extends JsonDeserializer<Movie> {

    // deserialize on class
    // have to define all fields
    @Override
    public Movie deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Movie movie = new Movie();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

            // Get the current property name
            String property = jsonParser.getCurrentName();

            // Move to the corresponding value
            jsonParser.nextToken();

            // Evaluate each property name and extract the value
            switch (property) {
                case "id" -> movie.setMovieId(jsonParser.getIntValue());
                case "budget" -> movie.setBudget(jsonParser.getIntValue());
                case "belongs_to_collection" -> {
                    Collection collection = getCollection(jsonParser);
                    movie.setBelongsToCollection(collection);
                }
                case "original_language" -> movie.setOriginalLanguage(jsonParser.getText());
                case "overview" -> movie.setOverview(jsonParser.getText().toUpperCase()); // custom processing example
                case "release_date" -> {
                    try {
                        movie.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonParser.getText()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "status" -> movie.setStatus(jsonParser.getText());
                case "title" -> movie.setTitle(jsonParser.getText());
                case "genres" -> {
                    List<Genre> genreList = getGenreList(jsonParser);
                    movie.setGenres(genreList);
                }
                case "production_companies" -> {
                    List<ProductionCompany> productionCompanyList = getProductionCompanyList(jsonParser);
                    movie.setProductionCompanies(productionCompanyList);
                }
                case "production_countries" -> {
                    List<ProductionCountry> productionCountryList = getProductionCountryList(jsonParser);
                    movie.setProductionCountries(productionCountryList);
                }
                // Unknown properties are ignored
            }
        }

        return movie;
    }

    private Collection getCollection(JsonParser jsonParser) throws IOException {

        if (jsonParser.currentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        // Check the first token
        if (jsonParser.currentToken() != JsonToken.START_OBJECT) {
            throw new IllegalStateException("Expected content to be an object");
        }

        Collection collection = new Collection();

        // Iterate over the tokens until the end of the array
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

            // Get the current property name
            String property = jsonParser.getCurrentName();

            // Move to the corresponding value
            jsonParser.nextToken();

            switch (property) {
                case "id" -> collection.setCollectionId(jsonParser.getIntValue());
                case "name" -> collection.setCollectionName(jsonParser.getText());
                case "poster_path" -> collection.setPosterPath(jsonParser.getText());
                case "backdrop_path" -> collection.setBackdropPath(jsonParser.getText());
            }
        }

        return collection;
    }

    private List<Genre> getGenreList(JsonParser jsonParser) throws IOException {

        // Check the first token
        if (jsonParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an object");
        }

        List<Genre> genreList = new ArrayList<>();

        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

            if (jsonParser.currentToken() != JsonToken.START_OBJECT) {
                throw new IllegalStateException("Expected content to be an object");
            }

            Genre genre = new Genre();

            // Iterate over the tokens until the end of the array
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

                // Get the current property name
                String property = jsonParser.getCurrentName();

                // Move to the corresponding value
                jsonParser.nextToken();

                switch (property) {
                    case "id" -> genre.setGenreId(jsonParser.getIntValue());
                    case "name" -> genre.setGenreName(jsonParser.getText());
                }
            }

            // Add each element of the array to the list of genre
            genreList.add(genre);
        }

        return genreList;
    }

    private List<ProductionCompany> getProductionCompanyList(JsonParser jsonParser) throws IOException {
        if (jsonParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an object");
        }

        List<ProductionCompany> productionCompanyList = new ArrayList<>();

        // Iterate over the tokens until the end of the array
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

            if (jsonParser.currentToken() != JsonToken.START_OBJECT) {
                throw new IllegalStateException("Expected content to be an object");
            }

            ProductionCompany productionCompany = new ProductionCompany();

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

                // Get the current property name
                String property = jsonParser.getCurrentName();

                // Move to the corresponding value
                jsonParser.nextToken();

                switch (property) {
                    case "id" -> productionCompany.setProductionCompanyId(jsonParser.getIntValue());
                    case "name" -> productionCompany.setProductionCompanyName(jsonParser.getText());
                    case "logo_path" -> productionCompany.setLogoPath(jsonParser.getText());
                    case "origin_country" -> productionCompany.setOriginCountry(jsonParser.getText());
                }
            }

            // Add each element of the array to the list of production company
            productionCompanyList.add(productionCompany);
        }

        return productionCompanyList;
    }

    private List<ProductionCountry> getProductionCountryList(JsonParser jsonParser) throws IOException {
        if (jsonParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an object");
        }

        List<ProductionCountry> productionCountryList = new ArrayList<>();

        // Iterate over the tokens until the end of the array
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

            if (jsonParser.currentToken() != JsonToken.START_OBJECT) {
                throw new IllegalStateException("Expected content to be an object");
            }

            ProductionCountry productionCountry = new ProductionCountry();

            // Iterate over the tokens until the end of the array
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                // Get the current property name
                String property = jsonParser.getCurrentName();

                // Move to the corresponding value
                jsonParser.nextToken();

                switch (property) {
                    case "iso_3166_1" -> productionCountry.setCountryCode(jsonParser.getText());
                    case "name" -> productionCountry.setCountryName(jsonParser.getText());
                }
            }

            // Add each element of the array to the list of production country
            productionCountryList.add(productionCountry);

        }

        return productionCountryList;
    }
}

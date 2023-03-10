package json.topics.deserialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import json.pojos.movies.partialjson.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class JacksonStreamingApiParsing {
    public PartialMovies readStreamMovies(String fileName) throws Exception {
        PartialMovies partialMovies = new PartialMovies();
        List<PartialMovie> partialMovieList = new ArrayList<>();

        // Create a factory for creating a JsonParser instance
        JsonFactory jsonFactory = new JsonFactory();

        // Create a JsonParser instance
        try (JsonParser jsonParser = jsonFactory.createParser(AutoParsing.class.getResourceAsStream("/%s".formatted(fileName)))) {

            // Check the first token
            while (jsonParser.currentToken() != JsonToken.START_ARRAY) {
                jsonParser.nextToken();
            }

            // Iterate over the tokens until the end of the array
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                // Read a contact and do something with it
                if (jsonParser.currentToken() != JsonToken.START_OBJECT) {
                    throw new IllegalStateException("Expected content to be an object");
                }

                PartialMovie partialMovie = new PartialMovie();
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

                    // Get the current property name
                    String property = jsonParser.getCurrentName();

                    // Move to the corresponding value
                    jsonParser.nextToken();

                    // Evaluate each property name and extract the value
                    switch (property) {
                        case "id":
                            partialMovie.setMovieId(jsonParser.getIntValue());
                            break;
                        case "budget":
                            partialMovie.setBudget(jsonParser.getIntValue());
                            break;
                        case "belongs_to_collection":
                            Collection collection = getCollection(jsonParser);
                            partialMovie.setBelongsToCollection(collection);
                            break;
                        case "original_language":
                            partialMovie.setOriginalLanguage(jsonParser.getText());
                            break;
                        case "overview":
                            partialMovie.setOverview(jsonParser.getText());
                            break;
                        case "release_date":
                            partialMovie.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonParser.getText()));
                            break;
                        case "status":
                            partialMovie.setStatus(jsonParser.getText());
                            break;
                        case "title":
                            partialMovie.setTitle(jsonParser.getText());
                            break;
                        case "genres":
                            List<Genre> genreList = getGenreList(jsonParser);
                            partialMovie.setGenres(genreList);
                            break;
                        case "production_companies":
                            List<ProductionCompany> productionCompanyList = getProductionCompanyList(jsonParser);
                            partialMovie.setProductionCompanies(productionCompanyList);
                            break;
                        case "production_countries":
                            List<ProductionCountry> productionCountryList = getProductionCountryList(jsonParser);
                            partialMovie.setProductionCountries(productionCountryList);
                            break;
                        // Unknown properties are ignored
                    }


                }
                partialMovieList.add(partialMovie);


            }
        } catch (Exception e) {
            throw new Exception(e);
        }

        partialMovies.setPartialMovies(partialMovieList);

        return partialMovies;
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
                case "id":
                    collection.setCollectionId(jsonParser.getIntValue());
                    break;
                case "name":
                    collection.setCollectionName(jsonParser.getText());
                    break;
                case "poster_path":
                    collection.setPosterPath(jsonParser.getText());
                    break;
                case "backdrop_path":
                    collection.setBackdropPath(jsonParser.getText());
                    break;
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
                    case "id":
                        genre.setGenreId(jsonParser.getIntValue());
                        break;
                    case "name":
                        genre.setGenreName(jsonParser.getText());
                        break;
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
                    case "id":
                        productionCompany.setProductionCompanyId(jsonParser.getIntValue());
                        break;
                    case "name":
                        productionCompany.setProductionCompanyName(jsonParser.getText());
                        break;
                    case "logo_path":
                        productionCompany.setLogoPath(jsonParser.getText());
                        break;
                    case "origin_country":
                        productionCompany.setOriginCountry(jsonParser.getText());
                        break;
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
                    case "iso_3166_1":
                        productionCountry.setCountryCode(jsonParser.getText());
                        break;
                    case "name":
                        productionCountry.setCountryName(jsonParser.getText());
                        break;
                }
            }

            // Add each element of the array to the list of production country
            productionCountryList.add(productionCountry);

        }

        return productionCountryList;
    }
}

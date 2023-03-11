package jackson.topics.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jackson.pojos.movies.Movies;
import jackson.topics.deserialization.AutoParsing;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class JsonToXml {
    public String convertMoviesJsonToXml(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        xmlMapper.setDateFormat(simpleDateFormat); // Jackson will serialize the Date to a timestamp format by default

        Movies movies = objectMapper.readValue(
                AutoParsing.class.getResourceAsStream("/%s".formatted(filename)),
                Movies.class);

        // write to file
        xmlMapper.writeValue(new File("movies.xml"), movies);

        // write to string
        String moviesXml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(movies);

        return moviesXml;
    }
}

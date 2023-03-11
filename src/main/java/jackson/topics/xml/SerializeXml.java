package jackson.topics.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jackson.pojos.movies.Movies;

import java.text.SimpleDateFormat;

public class SerializeXml {
    public String serialiseMovieXml(Movies movies) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        xmlMapper.setDateFormat(simpleDateFormat); // Jackson will serialize the Date to a timestamp format by default

        // write to string
        String moviesXml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(movies);

        return moviesXml;

    }
}

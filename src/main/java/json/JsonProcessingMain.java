package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.lessons.UseObjectMapper;
import json.pojos.Movies;

import java.nio.file.Paths;

public class JsonProcessingMain {
    public static void main(String[] args) throws Exception {
        // --------------- Read JSON with Object Mapper ---------------

        // From File
        UseObjectMapper.readFromFile();

        // From String
        UseObjectMapper.readFromString();

    }
}
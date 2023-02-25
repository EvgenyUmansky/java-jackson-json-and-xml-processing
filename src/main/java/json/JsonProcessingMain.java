package json;

import json.lessons.ParallelUseJsonNode;
import json.lessons.UseJsonNode;
import json.lessons.UseObjectMapper;

public class JsonProcessingMain {
    public static void main(String[] args) throws Exception {
        // --------------- Read JSON with Object Mapper ---------------

        // From File
        //UseObjectMapper.readFromFile();
        UseObjectMapper.readPartialJsonFromFile();
        UseJsonNode.parseJson();
        ParallelUseJsonNode.parseJson();

        // From String
        // UseObjectMapper.readFromString();

    }
}
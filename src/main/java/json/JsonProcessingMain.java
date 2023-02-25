package json;

import json.lessons.ParallelUseJsonNode;
import json.lessons.UseJsonNode;
import json.lessons.UseObjectMapper;

public class JsonProcessingMain {
    public static void main(String[] args) throws Exception {
        // --------------- Read JSON with Object Mapper ---------------

        // From File
        // UseObjectMapper.readFromFile();
//        JsonProcessingUtils.mergePartialJsonToFile(
//                Objects.requireNonNull(UseObjectMapper.class.getResource("/separate_movie_jsons"))
//                        .getFile()
//                        .replaceFirst("/", ""), // replace /C:/ to C:/
//                Objects.requireNonNull(UseObjectMapper.class.getResource(""))
//                        .getFile()
//                        .replaceFirst("/", ""), // replace /C:/ to C:/
//                "all_movies.json",
//                "movies");
        UseObjectMapper useObjectMapper = new UseObjectMapper();
        UseJsonNode useJsonNode = new UseJsonNode();
        ParallelUseJsonNode parallelUseJsonNode = new ParallelUseJsonNode();

        useObjectMapper.readPartialJsonFromFilePojo();
        useJsonNode.parseJson();
        parallelUseJsonNode.parseJson();


    }
}
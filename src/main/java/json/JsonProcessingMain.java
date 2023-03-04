package json;

import json.topics.deserialization.ManualTreeModelParallelParsing;
import json.topics.deserialization.ManualTreeModelParsing;
import json.topics.deserialization.AutoParsing;

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
        AutoParsing autoParsing = new AutoParsing();
        ManualTreeModelParsing manualTreeModelParsing = new ManualTreeModelParsing();
        ManualTreeModelParallelParsing manualTreeModelParallelParsing = new ManualTreeModelParallelParsing();

        autoParsing.readPartialJsonFromFilePojo();
        manualTreeModelParsing.parseJson();
        manualTreeModelParallelParsing.parseJson();


    }
}
package json;

public class JsonProcessingMain {
    public static void main(String[] args) throws Exception {
        JsonProcessingUtils.mergeJsonToFile(
                "C:\\Users\\eugum\\Desktop\\JavaCourses\\JsonXml\\java-json-xml-processing\\src\\main\\resources\\separate_movie_jsons",
                "C:\\Users\\eugum\\Desktop\\JavaCourses\\JsonXml\\java-json-xml-processing\\src\\main\\resources",
                "all_movies.json",
                "movies");
    }
}
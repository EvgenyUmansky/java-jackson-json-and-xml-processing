package json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonProcessingUtils {
    // --------------- Read Separate JSON Files -> Write Them to Single JSON File ---------------
    public static void mergeJsonToFile(String absoluteSourceDirPath, String absoluteTargetDirPath, String fileName, String metaKeyName) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode arrayNode = objectMapper.createArrayNode();
        ObjectNode root = JsonNodeFactory.instance.objectNode();

        // read all JSON files from source directory to string joiner
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(absoluteSourceDirPath))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path) && FilenameUtils.getExtension(path.toString()).equals("json")) {
                    JsonNode json = objectMapper.readTree(Files.readString(path, StandardCharsets.UTF_8));

                    // Can read JSON from many sources
                    // https://stackabuse.com/definitive-guide-to-jackson-objectmapper-serialize-and-deserialize-java-objects/
                    // JsonNode json = objectMapper.readTree(File);
                    // JsonNode json = objectMapper.readTree(String);
                    // JsonNode json = objectMapper.readTree(URL);
                    arrayNode.add(json);
                }
            }
        } catch (Exception e) {
            throw new Exception("[mergeJsonToFile] Failed to read {%s} source directory with JSON files"
                    .formatted(absoluteSourceDirPath), e);
        }

        FileUtils.forceMkdir(new File(absoluteTargetDirPath)); // create directory

        // write full JSON to file
        String targetFullPath = absoluteTargetDirPath + File.separator + fileName;
        File targetFile = new File(targetFullPath);

        Files.deleteIfExists(targetFile.toPath());

        root.putIfAbsent(metaKeyName, arrayNode);
        objectMapper.writeValue(targetFile, root); // write to file
    }

    // --------------- Read Separate JSON Files -> Write Them to String ---------------
    public static String mergeJsonToString(String absoluteSourceDirPath, String metaKeyName) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode arrayNode = objectMapper.createArrayNode();
        ObjectNode root = JsonNodeFactory.instance.objectNode();

        // read all JSON files from source directory to string joiner
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(absoluteSourceDirPath))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path) && FilenameUtils.getExtension(path.toString()).equals("json")) {
                    JsonNode json = objectMapper.readTree(Files.readString(path, StandardCharsets.UTF_8));

                    ((ObjectNode) json).remove("adult");
                    ((ObjectNode) json).remove("backdrop_path");
                    ((ObjectNode) json).remove("homepage");
                    ((ObjectNode) json).remove("imdb_id");
                    ((ObjectNode) json).remove("original_title");
                    ((ObjectNode) json).remove("poster_path");
                    ((ObjectNode) json).remove("revenue");
                    ((ObjectNode) json).remove("runtime");
                    ((ObjectNode) json).remove("spoken_languages");
                    ((ObjectNode) json).remove("tagline");
                    ((ObjectNode) json).remove("video");
                    ((ObjectNode) json).remove("vote_average");
                    ((ObjectNode) json).remove("vote_count");

                    arrayNode.add(json);
                }
            }
        } catch (Exception e) {
            throw new Exception("[mergeJsonToFile] Failed to read {%s} source directory with JSON files"
                    .formatted(absoluteSourceDirPath), e);
        }

        root.putIfAbsent(metaKeyName, arrayNode);

        return root.toString();
    }

    // --------------- Read Separate JSON Files -> Write Them to Single JSON File ---------------
    public static void mergePartialJsonToFile(String absoluteSourceDirPath, String absoluteTargetDirPath, String fileName, String metaKeyName) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode arrayNode = objectMapper.createArrayNode();
        ObjectNode root = JsonNodeFactory.instance.objectNode();

        // read all JSON files from source directory to string joiner
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(absoluteSourceDirPath))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path) && FilenameUtils.getExtension(path.toString()).equals("json")) {
                    JsonNode json = objectMapper.readTree(Files.readString(path, StandardCharsets.UTF_8));

                    ((ObjectNode) json).remove("adult");
                    ((ObjectNode) json).remove("backdrop_path");
                    ((ObjectNode) json).remove("homepage");
                    ((ObjectNode) json).remove("imdb_id");
                    ((ObjectNode) json).remove("original_title");
                    ((ObjectNode) json).remove("poster_path");
                    ((ObjectNode) json).remove("revenue");
                    ((ObjectNode) json).remove("runtime");
                    ((ObjectNode) json).remove("spoken_languages");
                    ((ObjectNode) json).remove("tagline");
                    ((ObjectNode) json).remove("video");
                    ((ObjectNode) json).remove("vote_average");
                    ((ObjectNode) json).remove("vote_count");

                    arrayNode.add(json);
                }
            }
        } catch (Exception e) {
            throw new Exception("[mergeJsonToFile] Failed to read {%s} source directory with JSON files"
                    .formatted(absoluteSourceDirPath), e);
        }

        FileUtils.forceMkdir(new File(absoluteTargetDirPath)); // create directory

        // write full JSON to file
        String targetFullPath = absoluteTargetDirPath + File.separator + fileName;
        File targetFile = new File(targetFullPath);

        Files.deleteIfExists(targetFile.toPath());

        root.putIfAbsent(metaKeyName, arrayNode);
        objectMapper.writeValue(targetFile, root); // write to file
    }
}

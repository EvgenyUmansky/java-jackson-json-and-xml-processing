package json;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class JsonProcessingUtils {
    public static void mergeJsonToFile(String absoluteSourceDirPath, String absoluteTargetDirPath, String fileName, String metaKeyName) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode arrayNode = objectMapper.createArrayNode();
        ObjectNode root = JsonNodeFactory.instance.objectNode();

        // read all JSON files from source directory to string joiner
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(absoluteSourceDirPath))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path) && FilenameUtils.getExtension(path.toString()).equals("json")) {
                    JsonNode json = objectMapper.readTree(Files.readString(path, StandardCharsets.UTF_8));
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

        root.putIfAbsent(metaKeyName, arrayNode);
        objectMapper.writeValue(new File(targetFullPath), root); // write to file
    }
}

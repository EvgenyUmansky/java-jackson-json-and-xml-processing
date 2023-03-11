package jackson.topics.compare;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.Comparator;

public class MovieComparator implements Comparator<JsonNode> {
    @Override
    public int compare(JsonNode o1, JsonNode o2) {
        if (o1.equals(o2)) {
            return 0;
        }

        if ((o1 instanceof TextNode) && (o2 instanceof TextNode)) {
            String text1 = o1.asText();
            String text2 = o2.asText();
            if (text1.equalsIgnoreCase(text2)) {
                return 0;
            }
        }

        return 1;
    }
}

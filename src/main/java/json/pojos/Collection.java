package json.pojos;

import lombok.Data;

import java.io.Serializable;

@Data
public class Collection implements Serializable {
    Integer collectionId;

    String backdropPath;

    String collectionName;

    String posterPath;
}

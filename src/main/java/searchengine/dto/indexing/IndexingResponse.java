package searchengine.dto.indexing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndexingResponse {
    private boolean result;
    private String error;

    @Override
    public String toString() {
        return "IndexingResponse{" +
                "result=" + result +
                ", error='" + error + '\'' +
                '}';
    }
}

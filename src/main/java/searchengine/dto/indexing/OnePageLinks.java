package searchengine.dto.indexing;

import java.util.ArrayList;

public class OnePageLinks extends ArrayList<String> {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OnePageLinks{");
        this.stream().forEach(str->sb.append(str).append("\n"));
        sb.append("modCount=").append(modCount);
        sb.append("}\n");
        return sb.toString();
    }
}

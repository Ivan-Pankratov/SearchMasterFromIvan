package searchengine.dto.indexing;

import java.util.ArrayList;

public class OnePageLinks extends ArrayList<String> {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OnePageLinks{");
        sb.append("modCount=").append(modCount);
        sb.append("}\n");
        return sb.toString();
    }
}

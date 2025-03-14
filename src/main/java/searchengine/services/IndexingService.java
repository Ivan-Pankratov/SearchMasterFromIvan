package searchengine.services;

import searchengine.dto.indexing.IndexingResponse;


public interface IndexingService {

    public IndexingResponse start();

    public IndexingResponse stop();

    public IndexingResponse addIndexing(String urlPage);
}

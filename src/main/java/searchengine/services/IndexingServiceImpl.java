package searchengine.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import searchengine.dto.indexing.OnePageLinks;

import searchengine.dto.indexing.IndexingResponse;
import searchengine.dto.indexing.ReadOnePage;


import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class IndexingServiceImpl implements IndexingService{
    private boolean statusRespons = false;

    private final String url = "https://www.larstornoe.com/";


    @Override
    public IndexingResponse start() {
        Logger.getLogger("start","start");
        if (statusRespons){
            return new IndexingResponse(false, "Индексация уже запущена");
        }
        statusRespons = true;
        OnePageLinks links = new ForkJoinPool()
                .invoke(new ReadOnePage("", url ));
        IndexingResponse response = new IndexingResponse();
        response.setResult(true);
        return response;
    }

    @Override
    public IndexingResponse stop() {
        return null;
    }

    @Override
    public IndexingResponse addIndexing(String urlPage) {
        Logger.getLogger(urlPage);
        OnePageLinks pageLinks = new ReadOnePage( "",urlPage).invoke();
        System.out.println(pageLinks.toString());
        IndexingResponse response = new IndexingResponse();
        response.setResult(true);
        return response;
    }
}

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
        if (statusRespons){
            return new IndexingResponse(false, "Индексация уже запущена");
        }
        statusRespons = true;
        OnePageLinks links = new ForkJoinPool()
                .invoke(new ReadOnePage("", "https://www.larstornoe.com/" ));
        IndexingResponse response = new IndexingResponse();
        response.setResult(true);
        return response;
    }

    @Override
    public IndexingResponse stop() {
        if (statusRespons){
            statusRespons = false;
            return new IndexingResponse(true, null);
        }
        return new IndexingResponse(false, "Индексация не запущена");
    }

    @Override
    public IndexingResponse addIndexing(String urlPage) {
        OnePageLinks pageLinks = new ReadOnePage( "",urlPage).invoke();
        System.out.println("Вот решение - " + pageLinks.toString());
        IndexingResponse response = new IndexingResponse();
        response.setResult(true);
        if (!response.isResult()){
            response.setError("Данная страница находится за пределами сайтов,\n" +
                    "указанных в конфигурационном файле");
        }
        return response;
    }
}

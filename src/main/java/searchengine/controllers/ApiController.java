package searchengine.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import searchengine.dto.indexing.IndexingResponse;
import searchengine.dto.statistics.StatisticsResponse;

import searchengine.services.IndexingService;
import searchengine.services.StatisticsService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final StatisticsService statisticsService;
    private final IndexingService indexingService;

    public ApiController(StatisticsService statisticsService, IndexingService indexingService) {
        this.statisticsService = statisticsService;
        this.indexingService = indexingService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public ResponseEntity<String> startIndexing(){
        return ResponseEntity.ok("start");
    }

    @GetMapping("/stopIndexing")
    public ResponseEntity<String> stopIndexing(){
        return ResponseEntity.ok("Индексация закончена");
    }

    @PostMapping("/indexPage")
    public ResponseEntity<String> addIndexing(String url){
        return ResponseEntity.ok(url);
    }
}

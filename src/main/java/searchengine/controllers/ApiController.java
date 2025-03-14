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
    private IndexingService indexingService;

    public ApiController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public ResponseEntity<IndexingResponse> startIndexing(){
        return ResponseEntity.ok(startIndexing().getBody());
    }

    @GetMapping("/stopIndexing")
    public ResponseEntity<String> stopIndexing(){
        return ResponseEntity.ok("Индексация закончена");
    }

    @PostMapping("/indexPage/{url}")
    public ResponseEntity<IndexingResponse> addIndexing(@PathVariable String url){
        return ResponseEntity.ok( indexingService.addIndexing(url));
    }
}

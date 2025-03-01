package searchengine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Entity
@Table(name = "site" )
@Getter
@Setter
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "INT", nullable = false)
    private Integer id ; //id INT NOT NULL AUTO_INCREMENT;


    /*
    //ENUM('INDEXING', 'INDEXED', 'FAILED')
    NOT NULL
    — текущий статус полной индексации сайта,
    отражающий готовность поискового движка осуществлять поиск
    по сайту — индексация или переиндексация в процессе,
    сайт полностью проиндексирован (готов к поиску)
    либо его не удалось проиндексировать
    (сайт не готов к поиску и не будет до устранения ошибок
     и перезапуска индексации);
     */
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum ('INDEXING', 'INDEXED', 'FAILED')", nullable = false )
    private SiteStatus status;

    /*
    — дата и время статуса
    (в случае статуса INDEXING дата и время должны обновляться регулярно
     при добавлении каждой новой страницы в индекс);
     */
    @Column(name = "status_time", columnDefinition = "DATETIME", nullable = false )
    private Date statusTime;

     //— текст ошибки индексации или NULL, если её не было;
    @Column(name = "last_error",  columnDefinition = "TEXT" )
    private String lastError;

//— адрес главной страницы сайта;
    @Column(name = "url",  columnDefinition = "VARCHAR(255)", nullable = false )
    private String url;

    //— имя сайта.
    @Column(name = "name",  columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    public Site() {
    }
}

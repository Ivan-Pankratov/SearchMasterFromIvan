package searchengine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "page", indexes = @Index(columnList = "path"))
class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "INT", nullable = false)
    private Integer id;

    // — ID веб-сайта из таблицы site;
    @ManyToOne
    @JoinColumn(name = "site_id", columnDefinition = "INT", nullable = false)
    private Site site;

    //— адрес страницы от корня сайта
    // (должен начинаться со слэша, например: /news/372189/);
    // изначально был тип ТЕКСТ, но заменил на "VARCHAR(255)", т.к. выходила ошибка при запуске:
    // "Caused by: java.sql.SQLSyntaxErrorException: BLOB/TEXT column 'path' used in key specification without a key length"
    @Column(name = "path", columnDefinition = "VARCHAR(255)", nullable = false)
    private String path;

    //— код HTTP-ответа,
    // полученный при запросе страницы (например, 200, 404, 500 или другие);
    @Column(name = "code", columnDefinition = "INT", nullable = false)
    private Integer code;

    //— контент страницы (HTML-код).
    @Column(name = "content", columnDefinition = "MEDIUMTEXT",  nullable = false)
    private String content;


    /*
    path TEXT NOT NULL — адрес страницы от корня сайта (должен начинаться со слэша, например: /news/372189/);
    code INT NOT NULL — код HTTP-ответа, полученный при запросе страницы (например, 200, 404, 500 или другие);
    content MEDIUMTEXT NOT NULL — контент страницы (HTML-код).

    По полю path должен быть установлен индекс, чтобы поиск по нему был быстрым, когда в нём будет много ссылок. Индексы рассмотрены в курсе «Язык запросов SQL».

     */

    public Page() {
    }
}

package searchengine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Поисковый индекс - Название класса изменено с Index на SearchIndex т.к. есть аналогичная аннотация
@Entity
@Table(name = "index_entity")
@Getter
@Setter
public class IndexEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",columnDefinition = "INT", nullable = false)
    private Integer id;

    // — идентификатор страницы;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", columnDefinition = "INT", nullable = false)
    private Page page;

    // — идентификатор леммы;
    @ManyToOne(cascade = CascadeType.MERGE,  fetch = FetchType.LAZY)
    @JoinColumn(name = "lemma_id", columnDefinition = "INT", nullable = false)
    private Lemma lemma;
/*
    //— количество данной леммы для данной страницы.
    @Column(name = "rank", columnDefinition = "FLOAT", nullable = false)
    private Float rank;
*/
    public IndexEntity() {
    }
}

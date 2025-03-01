package searchengine.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lemma")
public class Lemma {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "INT", nullable = false)
    private Integer id ;

    // — ID веб-сайта из таблицы site;
    @ManyToOne
    @JoinColumn(name = "site_id", columnDefinition = "INT", nullable = false)
    private Site site;

    //— нормальная форма слова (лемма);
    @Column(name = "lemma", columnDefinition = "VARCHAR(255)", nullable = false)
    private String lemma;

    /** — количество страниц,
     *  на которых слово встречается хотя бы один раз.
     *  Максимальное значение не может превышать общее количество слов на сайте.
     */
    @Column(name = "frequency", columnDefinition = "INT", nullable = false)
    private Integer frequency;

    public Lemma() {
    }
}


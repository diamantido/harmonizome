package edu.mssm.pharm.maayanlab.Harmonizome.model;

import javax.persistence.*;

@Entity
@Table(name = "user_search")
public class UserSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "keywords", unique = true)
    private String keywords;

    @Column(name = "count")
    private Integer count;

    public UserSearch() {
    }

    public UserSearch(String keywords) {
        this.keywords = keywords;
        this.count = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return keywords;
    }

    public void setText(String keywords) {
        this.keywords = keywords;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SolrData extends Model {

    @Id
    @Column(name="auto_id")
    public Long autoId;

    @Column(name="id")
    public String id;

    @Column(name="title")
    public String title;

    @Column(name="url")
    public String url;

    @Column(name="content")
    public String content;

    @Column(name="segment")
    public String segment;

    @Column(name="digest")
    public String digest;

    @Column(name="boost")
    public String boost;

    @Column(name="cache")
    public String cache;

    @Column(name="tstamp")
    public String tstamp;

    public SolrData(String id, String title, String url, String content, String segment,
                    String digest, String boost, String cache, String tstamp){
        this.id = id;
        this.title = title;
        this.url = url;
        this.content = content;
        this.segment = segment;
        this.digest = digest;
        this.boost = boost;
        this.cache = cache;
        this.tstamp = tstamp;
    }

    // Ebeans ORM finder
    public static Finder<Long, SolrData> find = new Finder<Long, SolrData>(Long.class, SolrData.class);

}

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

    /**
     * This finds a SolrData record by id
     * @param id Solr id, Which is string url.
     * @return SolrData object
     */
    public static SolrData getById(String id){
        return find.where()
        .eq("id", id).findUnique();
    }

    /**
     * This finds a SolrData record by url
     * @param url Solr url
     * @return SolrData object
     */
    public static SolrData getByUrl(String url){
        return find.where()
        .eq("url", url).findUnique();
    }

    /**
     * This inserts the solr raw data into the database;
     * @param id id of solr data
     * @param title title of solr data
     * @param url url of solr data
     * @param content content of solr data
     * @param segment segment of solr data
     * @param digest digest of solr data
     * @param boost boost of solr data
     * @param cache cache of solr data
     * @param tstamp tstamp of solr data
     * @return SolrData object
     */
    public static SolrData setData(String id, String title, String url, String content, String segment,
       String digest, String boost, String cache, String tstamp){
        SolrData data = new SolrData(id, title, url, content, segment, digest, boost, cache, tstamp);
        data.save();
        return data;
    }

    /**
     * This gets total count from solrData table.
     * @return integer of total count
     */
    public static int getCount(){
        return find.findRowCount();
    }

}

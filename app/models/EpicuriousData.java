package models;


import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

@Entity

public class EpicuriousData extends Model {

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

    public EpicuriousData(String id, String title, String url, String content, String segment,
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
    public static Finder<Long, EpicuriousData> find = new Finder<Long, EpicuriousData>(Long.class, EpicuriousData.class);

    /**
     * This finds a EpicuriousData record by id
     * @param id epicurious id, Which is string url.
     * @return EpicuriousData object
     */
    public static EpicuriousData getById(String id){
        return find.where()
                .eq("id", id).findUnique();
    }

    /**
     * This finds a EpicuriousData record by url
     * @param url epicurious url
     * @return EpicuriousData object
     */
    public static EpicuriousData getByUrl(String url){
        return find.where()
                .eq("url", url).findUnique();
    }

    /**
     * This inserts the epicurious raw data into the database;
     * @param id id of epicurious data
     * @param title title of epicurious data
     * @param url url of epicurious data
     * @param content content of epicurious data
     * @param segment segment of epicurious data
     * @param digest digest of epicurious data
     * @param boost boost of epicurious data
     * @param cache cache of epicurious data
     * @param tstamp tstamp of epicurious data
     * @return EpicuriousData object
     */
    public static EpicuriousData setData(String id, String title, String url, String content, String segment,
                                   String digest, String boost, String cache, String tstamp){
        EpicuriousData data = new EpicuriousData(id, title, url, content, segment, digest, boost, cache, tstamp);
        data.save();
        return data;
    }

    /**
     * This gets total count from EpicuriousData table.
     * @return integer of total count
     */
    public static int getCount(){
        return find.findRowCount();
    }

    /**
     * This gets all records from the data
     * @return List of EpicuriousData Object
     */
    public static List<EpicuriousData> getAll(){
        return find.all();
    }


}

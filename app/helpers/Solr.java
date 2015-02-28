package helpers;

import models.SolrData;
import org.codehaus.jackson.JsonNode;
import play.Logger;
import play.Play;


public class Solr {

    public static final String solrBaseUrl = Play.application().configuration().getString("solr.base.url");
    public static final String solrQuery = Play.application().configuration().getString("solr.query");
    public static final Integer solrMaxRows = Play.application().configuration().getInt("solr.max.rows");
    public static final Integer solrBatchSize = Play.application().configuration().getInt("solr.batch.size");
    public static final String solrFormat = Play.application().configuration().getString("solr.format");

    /**
     * This build the solr url that will use to query solr service.
     * @return String url
     */
    public static String buildQuery(int start, int rows){
        if(solrBaseUrl == null
                || solrQuery == null
                || solrFormat == null){
            Logger.error("One of your Solr configuration values is null. Check your application.conf");
        }
        String url = solrBaseUrl + "solr/select/?q="
                + solrQuery + "&start="
                + start + "&rows="
                + rows + "&wt="
                + solrFormat;
        Logger.info("Starting at [" + start + "] with [" + rows + "] rows -- Using url [" + url + "]");
        System.out.println();
        return url;
    }

    /**
     * This get the response from solr instance and builds values and inserts into db.
     * @param start the start point in rows pagination
     * @param rows of many rows at a time to insert
     */
    public static void buildInsert(int start, int rows){
        JsonNode rawResponse = Requests.get(Solr.buildQuery(start, rows)).get("response").get("docs");
        for(JsonNode response : rawResponse) {
            String id = response.get("id").getTextValue();
            String title = response.get("title").getTextValue();
            String content = response.get("content").getTextValue();
            String url = response.get("url").getTextValue();
            String segment = response.get("segment").getTextValue();
            String boost = response.get("boost").getDecimalValue().toString();
            String cache = response.get("cache").getTextValue();
            String digest = response.get("digest").getTextValue();
            String tstamp = response.get("tstamp").getTextValue();

            if (SolrData.getById(id) == null) {
                SolrData data = SolrData.setData(id, title, url, content, segment, digest, boost, cache, tstamp);
                Logger.info("Solr ID save to DB: " + data.id);
            } else {
                Logger.error("Solr ID already in DB: " + id);
            }
        }





    }

}

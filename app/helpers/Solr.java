package helpers;

import models.SolrData;
import org.codehaus.jackson.JsonNode;
import play.Logger;
import play.Play;
import java.util.*;


public class Solr {

    public static final String solrBaseUrl = Play.application().configuration().getString("solr.base.url");
    public static final String solrQuery = Play.application().configuration().getString("solr.query");
    public static final Integer solrMaxRows = Play.application().configuration().getInt("solr.max.rows");
    public static final Integer solrBatchSize = Play.application().configuration().getInt("solr.batch.size");
    public static final String solrFormat = Play.application().configuration().getString("solr.format");
    public static int successCount = 0;
    public static int duplicateCount = 0;

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
        System.out.println();
        Logger.info("Starting at [" + start + "] with [" + rows + "] rows -- Using url [" + url + "]");
        return url;
    }

    /**
     * This get the response from solr instance and builds values and inserts into db.
     * @param start the start point in rows pagination
     * @param rows of many rows at a time to insert
     */
    public static void buildInsert(int start, int rows){
        ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> failure = new HashMap<String, String>();
        JsonNode rawResponse = Requests.get(Solr.buildQuery(start, rows)).get("response").get("docs");
        for(JsonNode response : rawResponse) {
            String id = response.get("id") != null ? response.get("id").getTextValue() : "no id present";
            String title = response.get("title") != null ? response.get("title").getTextValue() : "no title present";
            String content = response.get("content") != null ? response.get("content").getTextValue() : "no content present";
            String url = response.get("url") != null ? response.get("url").getTextValue() : "no url present";
            String segment = response.get("segment") != null ? response.get("segment").getTextValue() : "no segment present";
            String boost = response.get("boost") != null ? response.get("boost").getDecimalValue().toString() : "no boost present";
            String cache = response.get("cache") != null ? response.get("cache").getTextValue() : "no cache present";
            String digest = response.get("digest") != null ? response.get("digest").getTextValue() : "no digest present";
            String tstamp = response.get("tstamp") != null ? response.get("tstamp").getTextValue() : "no tstamp present";

            if (SolrData.getById(id) == null) {
                SolrData data = SolrData.setData(id, title, url, content, segment, digest, boost, cache, tstamp);
                Logger.info("Solr ID save to DB: " + data.id);
                successCount++;
            } else {
              //  failure.put("failed", )
                Logger.error("Solr ID already in DB: " + id);
                duplicateCount++;
            }
        }

    }

}

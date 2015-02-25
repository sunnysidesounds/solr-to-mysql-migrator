package helpers;

import play.Logger;
import play.Play;


public class Solr {

    public static final String solrBaseUrl = Play.application().configuration().getString("solr.base.url");
    public static final String solrQuery = Play.application().configuration().getString("solr.query");
    public static final Integer solrStart = Play.application().configuration().getInt("solr.start");
    public static final Integer solrRows = Play.application().configuration().getInt("solr.rows");
    public static final String solrFormat = Play.application().configuration().getString("solr.format");

    /**
     * This build the solr url that will use to query solr service.
     * @return String url
     */
    public static String buildQuery(){
        if(solrBaseUrl == null
                || solrQuery == null
                || solrStart == null
                || solrRows == null
                || solrFormat == null){
            Logger.error("One of your Solr configuration values is null. Check your application.conf");
        }

        return solrBaseUrl + "solr/select/?q="
                + solrQuery + "&start="
                + solrStart + "&rows="
                + solrRows + "&wt="
                + solrFormat;
    }

}

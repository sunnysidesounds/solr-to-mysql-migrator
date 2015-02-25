package controllers;

import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import helpers.*;

public class SolrMigrator extends Controller {

    public static Result index(){

        return ok("Home page");
    }


    public static Result importData(){
        JsonNode rawResponse = Requests.get(Solr.buildQuery());
        JsonNode contentResponse = rawResponse.get("response").get("docs");
        for(JsonNode content : contentResponse){
            String id = content.get("id").asText();
            String title = content.get("title").asText();
            String url = content.get("url").asText();
            String segment = content.get("segment").asText();
            String digest = content.get("digest").asText();
            String boost = content.get("boost").asText();
            String cache = content.get("cache").asText();
            String tstamp = content.get("tstamp").asText();


            System.out.println(content);
            System.out.println("");
        }


        return ok(contentResponse);

    }



}

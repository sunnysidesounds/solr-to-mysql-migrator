package controllers;

import models.SolrData;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import helpers.*;
import play.Logger;

public class SolrMigrator extends Controller {

    public static Result index(){

        return ok("Home page");
    }


    public static Result importData(){

        int maxRows = 500;
        int batchSize = 100;
        int numberOfBatches = maxRows / batchSize;
        int startPoint = 0;
        int endPoint = 0;

        System.out.println();
        System.out.println("number of batches: " + numberOfBatches);

        for(int i = 1, j = 0; i <= numberOfBatches; i++, j++){
            startPoint = (j * batchSize);
            endPoint = (startPoint + batchSize);


            System.out.println("start: " + startPoint + " rows: " + batchSize);
        }

        System.out.println("end point: " + endPoint);

        if(maxRows != endPoint){
            batchSize = (maxRows - endPoint);
            System.out.println("New batch size: " + batchSize);

        }








       /*

        JsonNode rawResponse = Requests.get(Solr.buildQuery());
        JsonNode contentResponse = rawResponse.get("response").get("docs");
        int successful = 0;
        int failure = 0;
        for(JsonNode content : contentResponse){
            System.out.println(content);

            String title = content.get("title").asText() == null ? content.get("title").asText() : "no title";


            String id = content.get("id").asText() == null ? content.get("id").asText() : "no id";
            String title = content.get("title").asText() == null ? content.get("title").asText() : "no title";
            String ctn = content.get("content").asText() == null ? content.get("content").asText() : "no content";
            String url = content.get("url").asText() == null ? content.get("url").asText() : "no url";
            String segment = content.get("segment").asText() == null ? content.get("segment").asText() : "no segment";
            String digest = content.get("digest").asText() == null ? content.get("digest").asText() : "no digest";
            String boost = content.get("boost").asText() == null ? content.get("boost").asText() : "no boost";
            String cache = content.get("cache").asText() == null ? content.get("cache").asText() : "no cache";
            String tstamp = content.get("tstamp").asText() == null ? content.get("tstamp").asText() : "no tstamp";
            if(SolrData.getById(id) == null){
                SolrData data = SolrData.setData(id, title, url, ctn, segment, digest, boost, cache, tstamp);
                Logger.info("Solr ID save to DB: " + data.id);
                successful++;

            } else {
                Logger.info("Solr ID already in DB: " + id);
                failure++;
            }


        }
        //return ok(successful + " successfully imported, " + failure + " failed to import!");
        return ok(rawResponse);
          */

        return ok("");


    }



}

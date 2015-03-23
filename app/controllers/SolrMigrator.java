package controllers;

import models.SolrData;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import helpers.*;
import play.Logger;
import org.codehaus.jackson.node.ObjectNode;
import play.Play;
import play.libs.Json;

import java.math.BigDecimal;

public class SolrMigrator extends Controller {

    /**
     * Root just showing stats from db
     * @return JSON object db stats.
     */
    public static Result index(){
        ObjectNode parent = Json.newObject();
        parent.put("total_in_db", SolrData.getCount());
        return ok(parent);
    }


    /**
     * This import solr data into the database.
     * @return JSON object of import stats
     */
    public static Result importData(){

        int maxRows = Solr.solrMaxRows;
        int batchSize = Solr.solrBatchSize;
        int numberOfBatches = maxRows / batchSize;
        int startPoint = 0;
        int endPoint = 0;

        for(int i = 1, j = 0; i <= numberOfBatches; i++, j++){
            startPoint = (j * batchSize);
            endPoint = (startPoint + batchSize);
            Solr.buildInsert(startPoint, batchSize);
        }
        if(maxRows != endPoint){
            batchSize = (maxRows - endPoint);
            Solr.buildInsert(endPoint, batchSize);
        }

        ObjectNode parent = Json.newObject();
        ObjectNode child = Json.newObject();
        parent.put("import_status", child);
        child.put("success", Solr.successCount);
        child.put("number_of_batches", numberOfBatches);
        child.put("duplicates", Solr.duplicateCount);
        return ok(parent);
    }



}

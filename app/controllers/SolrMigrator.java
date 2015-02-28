package controllers;

import models.SolrData;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import helpers.*;
import play.Logger;

import java.math.BigDecimal;

public class SolrMigrator extends Controller {

    public static Result index(){

        return ok("Home page");
    }


    public static Result importData(){

        int maxRows = Solr.solrMaxRows;
        int batchSize = Solr.solrBatchSize;
        int numberOfBatches = maxRows / batchSize;
        int startPoint = 0;
        int endPoint = 0;
        System.out.println();
        System.out.println("number of batches: " + numberOfBatches);

        for(int i = 1, j = 0; i <= numberOfBatches; i++, j++){
            startPoint = (j * batchSize);
            endPoint = (startPoint + batchSize);
            Solr.buildInsert(startPoint, batchSize);
        }

        System.out.println("end point: " + endPoint);

        if(maxRows != endPoint){
            batchSize = (maxRows - endPoint);
            Solr.buildInsert(endPoint, batchSize);
        }





        return ok("");


    }



}

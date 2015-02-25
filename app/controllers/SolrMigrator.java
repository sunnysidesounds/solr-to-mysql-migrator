package controllers;

import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import helpers.*;

public class SolrMigrator extends Controller {

    public static Result index(){

        JsonNode response = Requests.get(Solr.buildQuery());


        return ok(response);
    }


    public static Result importData(){
        return TODO;

    }



}

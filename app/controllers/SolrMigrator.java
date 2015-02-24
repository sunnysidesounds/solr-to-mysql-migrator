package controllers;

import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import helpers.*;

public class SolrMigrator extends Controller {

    public static Result index(){

        JsonNode response = Requests.get("http://localhost:8983/solr/select/?q=url%3A%22http%3A%2F%2Fwww.epicurious.com%22,id&start=0&rows=10&wt=json");


        return ok(response);
    }


}

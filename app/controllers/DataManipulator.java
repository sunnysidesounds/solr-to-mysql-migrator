package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import models.SolrData;
import java.util.*;
import helpers.*;

public class DataManipulator extends Controller {


    public static Result analyzeEpicuriousRecipes(){

        int limit = 1000;
        List<SolrData> data = SolrData.getAll();

        int count = 0;
        int bLength = "http://www.epicurious.com/".length();
        for(SolrData value : data){

            String relativeUrl = value.id.substring(bLength-1);
            String[] relativeUrlArray = relativeUrl.split("-|\\/");
            int rArrLength = relativeUrlArray.length;

            if(rArrLength > 1){
                String lastArrayValue = relativeUrlArray[rArrLength-1];
                String isDashPresent = relativeUrl.substring((relativeUrl.length()-1)
                        - (lastArrayValue.length()-1)-1, (relativeUrl.length()-1)
                        - (lastArrayValue.length()-1));
                boolean isNumber = DataMod.isNumeric(lastArrayValue);

                System.out.println("Relative Url: " + relativeUrl);
                System.out.println("Last Value In Array: " + lastArrayValue);

                if(isNumber && isDashPresent.equals("-")){
                    System.out.println(relativeUrl + " last value is numberic and starts with a dash!");
                }

            }





            System.out.println("");


            if(count == limit){
                break;
            }


            count++;
        }




        return ok("Done!");
    }




}

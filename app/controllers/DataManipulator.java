package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import models.*;
import java.util.*;
import helpers.*;

public class DataManipulator extends Controller {

    /**
     * This takes data from solr_data table and extracts all the Epicurious recipes
     * @return Play Object
     */
    public static Result filterEpicuriousRecipes(){

        int limit = 1000;
        List<SolrData> data = SolrData.getAll();
        int successCount = 0;
        int duplicateCount = 0;

        int count = 0;
        int isRecipe = 0;
        int bLength = "http://www.epicurious.com/".length();
        for(SolrData value : data){

            String relativeUrl = value.id.substring(bLength-1);
            String[] relativeUrlArray = relativeUrl.split("-|\\/");
            int rArrLength = relativeUrlArray.length;
            boolean hasSlideshow = Arrays.asList(relativeUrlArray).contains("slideshows");

            if(rArrLength > 1){
                String lastArrayValue = relativeUrlArray[rArrLength-1];
                String isDashPresent = relativeUrl.substring((relativeUrl.length()-1)
                        - (lastArrayValue.length()-1)-1, (relativeUrl.length()-1)
                        - (lastArrayValue.length()-1));
                boolean isNumber = DataMod.isNumeric(lastArrayValue);

                System.out.println("[" + count + "] - Relative Url: " + relativeUrl);
                System.out.println("Last Value In Array: " + lastArrayValue);

                if(isNumber && isDashPresent.equals("-") && !hasSlideshow){
                    isRecipe++;
                    System.out.println(relativeUrl + " last value is numberic and starts with a dash!");
                    //TODO: Insert into epicurious table.

                    if (EpicuriousData.getById(value.id) == null) {
                        EpicuriousData dataInsert = EpicuriousData.setData(value.id, value.title, value.url, value.content, value.segment, value.digest, value.boost, value.cache, value.tstamp);
                        Logger.info("Epicurious ID save to DB: " + dataInsert.id);
                        successCount++;
                    } else {
                        //  failure.put("failed", )
                        Logger.error("Epicurious ID already in DB: " + value.id);
                        duplicateCount++;
                    }

                }
            }

            System.out.println("");
            count++;
        }
        System.out.println("Total matching epicusious recipe urls: " + isRecipe);
        return ok("Done!");
    }


    /**
     * This changes our recipe title to a title casing.
     * @return
     */
    public static Result formatRecipeTitles() {

        List<Recipe> data = Recipe.getAll();
        for(Recipe value : data){

            if(DataMod.isUpperCased(value.title)){
                System.out.println("Title is UPPERCASE : " +  value.title);
                String titleCaseString = DataMod.toTitleCase(value.title);
                Recipe.updateData(value.id, titleCaseString, value.sourceUrl, value.photoUrl, value.preparation);
                System.out.println("    Changing to : " + titleCaseString);
            } else {
                System.out.print("Title : " +  value.title + "\n");
            }


        }

        return ok("Completed title casing updates");
    }



}

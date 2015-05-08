package controllers;

import models.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.mvc.Controller;
import play.mvc.Result;
import java.io.IOException;
import java.util.*;


public class DOMParser extends Controller {


    public static Result parseEpicurious() throws IOException{

        int count = 0;
        int limit = 33253;
        String baseUrl = "http://www.epicurious.com";
        List<EpicuriousData> data = EpicuriousData.getAll();

        for(EpicuriousData value : data){

            if(Recipe.getBySourceUrl(value.id) == null){
                Document doc = Jsoup.connect(value.id).get();


                System.out.println("-----------------------------------------");
                System.out.println("RECIPE ID: " + value.autoId + " -- URL:" + value.id);
                System.out.println("");

                //Get title
                System.out.println("TITLE: ");
                Elements title = doc.select("#headline h1");
                System.out.println(title.text());
                System.out.println("");

                // Get photo
                String photo = null;
                System.out.println("PHOTO: ");
                String relativeUrl = doc.select(".photo").attr("src");
                if(!relativeUrl.equals("")) {
                    photo = baseUrl + relativeUrl;
                    System.out.println(photo);
                }


                System.out.println("");

                // Get preparation
                Elements preparation = doc.select("#preparation");
                System.out.println("PREPARATION: ");
                System.out.println(preparation.text());


                Recipe saved = Recipe.setData(
                        title.text(),
                        value.id,
                        photo,
                        preparation.text()
                );

                // Get ingredients
                System.out.println("INGREDENTS: ");
                Elements ingredientParent = doc.select(".ingredient");
                for(Element ingredientChild : ingredientParent ){
                    System.out.println(ingredientChild.text());
                    Ingredient.setData(
                            saved.id,
                            ingredientChild.text(),
                            null
                    );

                }
                System.out.println("");

            } else {
                System.out.println(value.id + " already exists");
            }




            System.out.println("-----------------------------------------");
            System.out.println("");
            if(count == limit){
                break;
            }

            count++;

        }

        return ok("completed");
    }





}

package controllers;

import models.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.mvc.Controller;
import play.mvc.Result;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;
import helpers.DataMod;

public class DOMParser extends Controller {


    public static Result parseEpicurious() throws IOException{

        int count = 0;
        int limit = 33253;
        //int limit = 3;
        String baseUrl = "http://www.epicurious.com";
        List<EpicuriousData> data = EpicuriousData.getAll();

        for(EpicuriousData value : data){

            if(Recipe.getBySourceUrl(value.id) == null){

                try {
                    Document doc = Jsoup.connect(value.id).get();

                    System.out.println("-----------------------------------------");
                    System.out.println("RECIPE ID: " + value.autoId + " -- URL:" + value.id);
                    System.out.println("");

                    //Get title
                    System.out.println("TITLE: ");
                    Elements title = doc.select(".title-source h1");
                    System.out.println(title.text());
                    System.out.println("");

                    // Get photo
                    String photo = null;
                    System.out.println("PHOTO: ");
                    Element div = doc.select("div.social-img").first();
                    if(div != null) {
                        Element img = div.select("img.responsive").first();
                        String rawImg = img.attr("gumby-media");
                        ArrayList linksList = DataMod.pullLinks(rawImg);
                        String presumbedImgUrl = linksList.get(linksList.size() - 1).toString();
                        if (!presumbedImgUrl.equals("")) {
                            photo = presumbedImgUrl;
                            System.out.println(photo);
                        }
                    } else {
                        System.out.println("No Image");
                    }

                    System.out.println("");

                    // Get preparation
                    Elements preparation = doc.select(".preparation-groups");
                    System.out.println("PREPARATION: ");
                    System.out.println(preparation.text());


                    Recipe saved = Recipe.setData(
                            title.text(),
                            value.id,
                            photo,
                            preparation.text()
                    );

                    System.out.println("");

                    // Get ingredients
                    System.out.println("INGREDENTS: ");
                    Elements ingredientParent = doc.select(".ingredient");
                    for (Element ingredientChild : ingredientParent) {
                        System.out.println(ingredientChild.text());
                        Ingredient.setData(
                                saved.id,
                                ingredientChild.text(),
                                null
                        );

                    }
                    System.out.println("");

                } catch (NullPointerException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (HttpStatusException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



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

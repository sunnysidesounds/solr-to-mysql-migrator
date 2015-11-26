package models;

import play.db.ebean.Model;
import java.sql.Timestamp;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.Version;
import org.codehaus.jackson.annotate.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Recipe extends Model {

    @Id
    @Column(name="id")
    public Long id;

    @Column(name="title")
    public String title;

    @Column(name="source_url")
    public String sourceUrl;

    @Column(name="photo_url")
    public String photoUrl;

    @Column(name="preparation")
    public String preparation;

    @JsonProperty("created_at")
    @Column(name="created_at")
    public Timestamp createdAt;

    @Version
    @JsonProperty("modified_at")
    @Column(name="modified_at")
    public Timestamp modifiedAt;

    public Recipe(String title, String sourceUrl, String photoUrl, String preparation){
        this.title = title;
        this.sourceUrl = sourceUrl;
        this.photoUrl = photoUrl;
        this.preparation = preparation;
        this.createdAt = new Timestamp(new Date().getTime());
    }

    // Ebeans ORM finder
    public static Finder<Long, Recipe> find = new Finder<Long, Recipe>(Long.class, Recipe.class);

    /**
     * This gets a recipe record by auto-incremental id
     * @param id auto-incremental id
     * @return Obj
     */
    public static Recipe getById(Long id){
        return find.where()
                .eq("id", id).findUnique();
    }

    /**
     * This updates a recipe record
     * @param id recipe id
     * @param title recipe title
     * @param sourceUrl where we got the recipe
     * @param photoUrl the photo of the recipe
     * @param preparation who to prepare the recipe, instructions
     * @return
     */
    public static Recipe updateData(Long id, String title, String sourceUrl, String photoUrl, String preparation){
        Recipe updateRecipe = Recipe.getById(id);
        updateRecipe.updateRecipe(title, sourceUrl, photoUrl, preparation);
        updateRecipe.update();
        return updateRecipe;
    }

    /**
     * Supports updateData
     * @param t recipe id
     * @param s recipe title
     * @param p where we got the recipe
     * @param pre who to prepare the recipe, instructions
     */
    public void updateRecipe(final String t, final String s, final String p, final String pre){
        title = t;
        sourceUrl = s;
        photoUrl = p;
        preparation = pre;
    }


    /**
     * This gets a recipe record by source_url
     * @param sourceUrl The sourceURL
     * @return Obj
     */
    public static Recipe getBySourceUrl(String sourceUrl){
        return find.where()
                .eq("source_url", sourceUrl).findUnique();
    }

    /**
     * This gets all recipe records
     * @return List of Obj
     */
    public static List<Recipe>getAll(){
        return find.all();
    }

    /**
     * This inserts recipe record
     * @param title title of recipe
     * @param sourceUrl the url where we got the recipe
     * @param photoUrl the url of the photo
     * @param preparation recipe preparation details
     * @return Returns saved Obj
     */
    public static Recipe setData(String title, String sourceUrl, String photoUrl, String preparation){
        Recipe data = new Recipe(title, sourceUrl, photoUrl, preparation);
        data.save();
        return data;
    }

}

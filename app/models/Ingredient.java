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
public class Ingredient extends Model {
    @Id
    @Column(name="id")
    public Long id;

    @Column(name="recipe_id")
    public Long recipeId;

    @Column(name="from_recipe_ingredient")
    public String fromRecipeIngedient;

    @Column(name="generic_ingredient")
    public String genericIngredient;

    @JsonProperty("created_at")
    @Column(name="created_at")
    public Timestamp createdAt;

    @Version
    @JsonProperty("modified_at")
    @Column(name="modified_at")
    public Timestamp modifiedAt;

    public Ingredient(Long recipeId, String fromRecipeIngedient, String genericIngredient){
        this.recipeId = recipeId;
        this.fromRecipeIngedient = fromRecipeIngedient;
        this.genericIngredient = genericIngredient;
    }

    // Ebeans ORM finder
    public static Finder<Long, Ingredient> find = new Finder<Long, Ingredient>(Long.class, Ingredient.class);

    /**
     * This gets a ingredient by id
     * @param id auto-incremental id
     * @return Obj
     */
    public static Ingredient getById(Long id){
        return find.where()
                .eq("id", id).findUnique();
    }

    /**
     * This getd all ingredients
     * @return List of Obj
     */
    public static List<Ingredient>getAll(){
        return find.all();
    }

    /**
     * This gets a list of generic ingredients, not unquie
     * @param genericIngredient generic ingredient, (i.e. flour, not 1 cup of flour)
     * @return List of Obj
     */
    public static List<Ingredient> getByGenericIngredient(String genericIngredient){
        return find.where()
                .eq("generic_ingredient", genericIngredient).findList();
    }

    public static Ingredient setData(Long recipeId, String fromRecipeIngedient, String genericIngredient){
        Ingredient data = new Ingredient(recipeId, fromRecipeIngedient, genericIngredient);
        data.save();
        return data;
    }

}







package example.com.samsung.afinal.Classes;

import java.util.List;

/**
 * Created by samsung on 4/13/2018.
 */

public class Recipe {
    private int id;                     // id
    private String title;               //레시피 제목
    private User publisher;             //작성자
    private List<String> ingredients;   //재료목록
    private List<String> recipeText;    //레시피 텍스트
    private int favoriteNum;            //favorite 개수
    private int cookingMinutes;         //조리시간 (분 단위)
    /* TODO: 이미지 타입 int로 해도 괜찮은지 */
    private int thumnailImage;          //썸네일이미지

    public Recipe(int _id, String _title, User _publisher,
                  List<String> _ingredients, List<String> _recipeText,
                  int _favoriteNum, int _cookingMinutes, int _thumnailImage){
        this.id = _id;
        this.title = _title;
        this.publisher = _publisher;
        /* TODO : List assign 이거 맞는지 체크하기*/
        this.ingredients = _ingredients;
        this.recipeText = _recipeText;
        this.favoriteNum = _favoriteNum;
        this.cookingMinutes = _cookingMinutes;
        this.thumnailImage = _thumnailImage;
    }

    public int getId(){return this.id;}
    public String getTitle(){return this.title;}
    public User getPublisher(){return this.publisher;}
    public List<String> getIngredients(){return this.ingredients;}
    public List<String> getRecipeText(){return this.recipeText;}
    public int getFavoriteNum(){return this.favoriteNum;}
    public int getCookingMinutes(){return this.cookingMinutes;}
    public int getThumnailImage(){return this.thumnailImage;}

    public void setTitle(String newTitle){this.title = newTitle;}
    public void setIngredients(List<String> newIngredients){this.ingredients = newIngredients;}
    public void setRecipeText(List<String> newRecipeText){this.recipeText = newRecipeText;}
    public void setFavoriteNum(int newFavoriteNum){this.favoriteNum = newFavoriteNum;}
    public void setCookingMinutes(int newCookingMinutes){this.cookingMinutes = newCookingMinutes;}
    public void setThumnailImage(int newThumnailImage){this.thumnailImage = newThumnailImage;}

}

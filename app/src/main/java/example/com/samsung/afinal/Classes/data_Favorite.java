package example.com.samsung.afinal.Classes;

public class data_Favorite {

    private int img;
    private String text;

    public data_Favorite(int img, String text){
        this.img = img;
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public int getImg(){
        return this.img;
    }

}

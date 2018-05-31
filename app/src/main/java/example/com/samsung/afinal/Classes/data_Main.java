package example.com.samsung.afinal.Classes;


/*
* Main Content 틀 하나에 들어가는 template같은 것
*
*
* */

public class data_Main {
    private int imageURL;
    private String title;
    private String context;
    public data_Main(String title, int imageURL, String context){
        this.title = title;
        this.imageURL = imageURL;
        this.context = context;
    }

    public int getImageURL() { return this.imageURL; }
    public String getTitle(){return this.title;}
    public String getContext(){return this.context;}
    public void setImageURL(int imageURL){this.imageURL = imageURL;}
    public void setTitle(String title){this.title = title;}
    public void setContext(String context){this.context = context;}
}

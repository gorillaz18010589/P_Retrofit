package tw.org.iii.appps.p_retrofit.Model;
//1.bean物件
//2.getter設定
import com.google.gson.annotations.SerializedName;

//@SerializedName 是Gson用於將JSON的key對映到Java物件的欄位的
public class Post {
    private int userId;

    private int id;

    private String title;

    @SerializedName("body") //要對應的欄位
    private String text; //自訂的名稱對應此欄位


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
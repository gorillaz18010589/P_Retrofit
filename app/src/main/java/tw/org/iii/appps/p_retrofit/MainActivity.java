package tw.org.iii.appps.p_retrofit;
//Retrofit 是一個用於Android 和Java 的型別安全的HTTP 客戶端。它通過將REST web service 的API轉換成Java的介面,以簡化HTTP 連線的處理。
// 在這裡,我將告訴你怎麼使用這個在Android 中最常用並且最被推薦的HTTP 庫。
// 這個強大的庫可以很容易地處理JSON 或者XML 資料,然後轉換成POJO。GET,POST,PUT,PATCH,和DELETE這些請求都可以執行。
// 和大多數開源軟體一樣,Retrofit 也是構建在其他強大的庫之上。在底層,Retrofit 使用OKHttp(來自同一個開發者)來處理網路請求。同樣,Retrofit
// 也並沒有自己構建JSON 轉換器來轉換JSON 資料,相反,它通過支援下面一些JSON 轉換庫來將JSON 資料轉換成Java 物件
//Gson:將JSON轉為對應Jaca的api
//enqueue() 非同步地傳送請求, 然後當響應回來的時候, 使用回撥的方式通知你的APP。因為這個請求是非同步的, Retrofit 使用一個另外的執行緒去執行它, 這樣UI 執行緒就不會被阻塞了

//免費api串接:提供的REST API, 這是一個用於測試和原型設計的線上模擬 API。https://jsonplaceholder.typicode.com/
//uri:https://jsonplaceholder.typicode.com/posts

//目的串接別人寫的api,用get方式取得

//1.加入Api,網路權限打開
//implementation  'com.squareup.retrofit2:converter-gson'
//implementation 'com.squareup.retrofit2:retrofit:2.6.2'
//implementation 'com.squareup.retrofit2:converter-scalars:2.1.0'
//2.做出Post bean出來
//3.xml配置
//4.JsonPlaceHolderApi interface建立API介面: //建立一個APIService 的介面。這個介面包含了將要用到的傳送POST, PUT 和DELETE 請求的方法。
//5./posts
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tw.org.iii.appps.p_retrofit.Model.Commment;
import tw.org.iii.appps.p_retrofit.Model.InterFace.JsonPlaceHolderApi;
import tw.org.iii.appps.p_retrofit.Model.Post;

public class MainActivity extends AppCompatActivity {
    private TextView txt_view_result;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Retrofit.Builder():
        //Retrofit.baseUrl(String baseUrl)://設置baseUrl即要連的網站(回傳值Builder )
        //Retrofit.addConverterFactory(Converter.Factory factory)://新增一個轉換工廠(這次用Gson工廠)
        //Retrofit.build():(回傳值Retrofit)
        //Retrofit.create(final Class<T> service)://創造service(這邊是自己寫好的Jsonp_api介面)(回傳你自己訂自的<T>資料結構)
        //enqueue(Callback<T> callback)://

        //1.init
        txt_view_result = findViewById(R.id.txt_view_result);



        //2.bulid Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/") //1.要連接的api網址
                .addConverterFactory(GsonConverterFactory.create())//2.新增的工廠類型(這次用Gson)
                .build();

        //3.retrofit.create (寫好的介面類別)
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class); //將自己做好的retrofit.建立一個service(這邊是自己寫好的Jsonp_api介面)(回傳你自己訂自的<T>資料結構)丟進去自己寫好的介面資料結構

        getPosts();
//        getComments();

    }

    private void getComments() {
        Call<List<Commment>> call = jsonPlaceHolderApi.getComment(4); //這個jsonPlaceHolderApi,裡自訂的方法取得(int postid == 頁面)
        call.enqueue(new Callback<List<Commment>>() { //回乎回來剩List<Comment>,非同步的執行

            @Override
            public void onResponse(Call<List<Commment>> call, Response<List<Commment>> response) {
                if(! response.isSuccessful()){
                    txt_view_result.setText("Code" +response.code());
                }

                List<Commment> commments  = response.body();
                for(Commment commment : commments){
                    String content ="";
                    content += "Id:" + commment.getId() + " \n";
                    content += "postId:" + commment.getPostId() + " \n";
                    content += "name:" + commment.getName() + " \n";
                    content += "email:" + commment.getEmail() + " \n";
                    content += "text:" + commment.getText() + " \n";

                    txt_view_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Commment>> call, Throwable t) {
                Log.v("hank","Comment失敗:" + t.getMessage());
            }
        });
    }

    private void getPosts() {
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(5); //getPosts(@Query("userId") int userId);//(1.@Query(參數節點) ,2.參數質)

        //4.enqueue()非同步地傳送請求, 然後當響應回來的時候, 使用回撥的方式通知你的APP。因為這個請求是非同步的, Retrofit 使用一個另外的執行緒去執行它, 這樣UI 執行緒就不會被阻塞了。
        call.enqueue(new Callback<List<Post>>() {

            //*在收到HTTP 響應的時候被呼叫。這個方法在伺服器可以處理請求的情況下呼叫, 即使伺服器返回的是一個錯誤的資訊。例如你獲取到的響應狀態是404 或500。你可以使用response.code() 來獲取狀態碼, 以便進行不同的處理. 當然你也可以直接用isSuccessful() 方法來判斷響應的狀態碼是不是在200-300 之間(在這個範圍內標識是成功的)。
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){ //如果回呼回來不成功時
                    txt_view_result.setText("Code" + response.code());
                    Log.v("hank","!response.isSuccessful()");
                }

                //將回來的body訊息,尋訪一個一個拿出來,因為Gson處理好了解Json的事情
                List<Post> posts = response.body();
                for( Post post : posts){
                    String content="";
                    content += "ID :" + post.getId() + " \n" ;
                    content += "userId :" + post.getUserId() + " \n" ;
                    content += "Title :" + post.getTitle() + " \n" ;
                    content += "text :" + post.getText() + " \n" ;

                    txt_view_result.append(content);
                }
                Log.v("hank","onResponse" +response.toString());

            }

            //*當和伺服器通訊出現網路異常時, 或者在處理請求出現不可預測的錯誤時, 會呼叫這個方法。
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.v("hank","onFailure" + t.getMessage());
            }
        });
    }
}
package tw.org.iii.appps.p_retrofit.Model.InterFace;

//https://jsonplaceholder.typicode.com/"posts"
//目的用Get方式
//1.準備好呼叫資料結構,裡面包List<Post(代表我們自己寫的bean)>,用List因為 key:value,getPosts()是自己定義的方法
//2. @GET("posts") //1@GET代表使用的呼叫方法,2.("posts")是Get帶的?參數
//====================================================

//https://jsonplaceholder.typicode.com/posts/1/comments
//1.bean物件 Comment
//2.Get三個參數,定義取得方法

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import tw.org.iii.appps.p_retrofit.Model.Commment;
import tw.org.iii.appps.p_retrofit.Model.Post;

public interface JsonPlaceHolderApi {
//    interface Call<T> ://呼叫資料結構,裡面包List<Post(代表我們自己寫的bean)>,用List因為 key:value

    //1 /posts 一個/post的抓法
//    @GET("posts") //1@GET代表使用的呼叫方法,2.("posts")是Get帶的?參數
//    Call<List<Post>> getPosts();//1.呼叫資料結構,裡面包List<Post(代表我們自己寫的bean)>,用List因為 key:value,getPosts()


    //1.2  /posts?userId=1 問號帶參數的方式
//    @GET("posts") //1@GET代表使用的呼叫方法,2.("posts")是Get帶的?參數
//    Call<List<Post>> getPosts(@Query("userId") int userId);//(1.@Query(參數節點) ,2. 參數質)

    //1.3  /posts?userId=1&_sort=id&order=desc 問號帶參3個參數方法
//    @GET("posts")
//    Call<List<Post>> getPosts(
//            @Query("userId") int userId, //(1.@Query(參數節點) ,2. 參數質)
//            @Query("_sort") String sort,
//            @Query("_order") String orger
//    );

    //1.4 //posts?userId=5&userId=6 userId 同個欄位帶兩個參數
//    @GET("posts")
//    Call<List<Post>> getPosts(
//            @Query("userId") Integer userId1,
//            @Query("userId") Integer userId2
//    );

    //1.5 https://jsonplaceholder.typicode.com/posts?userId=2&userId=8&userId=10  //同一個userId欄位,一次帶三個int參數
    @GET("posts")
    Call<List<Post>> getPosts(
           @Query("userId") Integer[] userId
    );
    //@QueryMap註解會把參數拼接到url後面
    //1.6   /posts?userId=1&_sort=id&order=desc 用Map搭配HashMap key:value方式帶入三個不同參數進去
    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String,String>parameters);


//    2.1方法三個參數//https://jsonplaceholder.typicode.com/posts/2/comments  ,/2/:這個參數代表頁面如果用這個方法,要寫100遍所以改用下面2.2方法
    @GET("posts/2/comments")
    Call<List<Commment>> getComment();

    //2.2方法三個參數用{id}取代//https://jsonplaceholder.typicode.com/posts/2/comments
    @GET("posts/{id}/comments") //{id}用參數帶
    Call<List<Commment>> getComment(@Path("id") int postId); //(1.路徑("id")對應{id},2.參數(頁面id))

    //===================================================================
    //Post
    //3.1
    @POST("posts")//posts //:Post post = new Post(2, "Title", "text"); 建構式帶入參數後回傳 id:101, tilte:title, text:text (Post是異動資料)
    Call<Post>createPost(@Body Post post);//被@Body注解的ask将会被Gson转换成json发送到服务器，返回到Post。 // 其中返回类型为Call<*>，*是接收数据的类

    //3.2 posts  // userId=23&title=New%20Title&body=New%20Text
    @FormUrlEncoded
    @POST("posts")
    Call<Post>createPost( //帶入三個不同參數傳遞
            @Field("userId")int usetId,// @Filed("自訂參數名")參數
            @Field("title")String title,
            @Field("body")String text
            );

    //3.3 posts //用Hash map,put key:value方式給傳遞參數
    @FormUrlEncoded
    @POST("posts") //用Hash map,put key:value方式
    Call<Post>createPost(@FieldMap Map<String,String> filed );
}

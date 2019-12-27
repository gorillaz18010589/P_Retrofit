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

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tw.org.iii.appps.p_retrofit.Model.Commment;
import tw.org.iii.appps.p_retrofit.Model.Post;

public interface JsonPlaceHolderApi {
//    interface Call<T> ://呼叫資料結構,裡面包List<Post(代表我們自己寫的bean)>,用List因為 key:value

    //1 /posts 一個/post的抓法
//    @GET("posts") //1@GET代表使用的呼叫方法,2.("posts")是Get帶的?參數
//    Call<List<Post>> getPosts();//1.呼叫資料結構,裡面包List<Post(代表我們自己寫的bean)>,用List因為 key:value,getPosts()


    //1.2  /posts?userId=1 問號帶參數的方式
    @GET("posts") //1@GET代表使用的呼叫方法,2.("posts")是Get帶的?參數
    Call<List<Post>> getPosts(@Query("userId") int userId);//(1.@Query(參數節點) ,2. 參數質)





//    2.1方法三個參數//https://jsonplaceholder.typicode.com/posts/2/comments  ,/2/:這個參數代表頁面如果用這個方法,要寫100遍所以改用下面2.2方法
//    @GET("posts/2/comments")
//    Call<List<Commment>> getComment();

    //2.2方法三個參數用{id}取代//https://jsonplaceholder.typicode.com/posts/2/comments
    @GET("posts/{id}/comments") //{id}用參數帶
    Call<List<Commment>> getComment(@Path("id") int postId); //(1.路徑("id")對應{id},2.參數(頁面id))


}

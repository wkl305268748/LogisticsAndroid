package test1.retrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Kenny on 2017/9/12.
 */

public class Client {
    public static void main(String[] args){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:82/")
                .build();
        HttpApi service = retrofit.create(HttpApi.class);

        Call<ResponseBody> repos = service.login("wkl","123456");
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

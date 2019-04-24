package cn.yuliang.okhttp.demo;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author yuliang
 */
public class MainActivity extends AppCompatActivity {
    private TextView textView;
//    final RxPermissions rxPermissions = new RxPermissions(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
       /* rxPermissions
                .request(Manifest.permission.INTERNET)
                .subscribe();*/
    }

    public void postHttp(View view) {

    }

    public void getHttp(View view) {
        textView.setText("查看log，tag： getHttp");
        //创建Client 的客户端
        OkHttpClient client = new OkHttpClient();
        //创建一个Request的请求
        Request request = new Request.Builder().url("https://www.baidu.com").build();

        //创建Call 获取异步数据
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("getHttp",response.body().string());
            }
        });


    }

    //http 的同步请求
    private void getHttp(){
        //创建Client 的客户端
        OkHttpClient client = new OkHttpClient();
        //创建一个Request的请求
        Request request = new Request.Builder().url("https://www.baidu.com").build();

        //创建Call 获取异步数据
        Call call = client.newCall(request);
        //Response 在OKHttp 当中的响应,同步的方法
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

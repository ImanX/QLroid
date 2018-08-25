package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.github.imanx.QLroid.Mutation;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.callback.Callback;
import com.github.imanx.QLroid.request.Argument;
import com.github.imanx.QLroid.request.Header;
import com.github.imanx.QLroid.request.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG_A";

    private static String baseUrl = "http://192.168.66.115/api/graphql/my";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txt = findViewById(R.id.txt);


        String token  = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImJmZWMyOTU4NzgwYWQ0ZTliYjI0YTZkMDQ4ZjI3NzBiN2IyODcwMWU5N2E2YmNhMGNhYmFmM2I0OGJjNGM2NWQ5YTNkODc2NjZiNmZhODRlIn0.eyJhdWQiOiIxIiwianRpIjoiYmZlYzI5NTg3ODBhZDRlOWJiMjRhNmQwNDhmMjc3MGI3YjI4NzAxZTk3YTZiY2EwY2FiYWYzYjQ4YmM0YzY1ZDlhM2Q4NzY2NmI2ZmE4NGUiLCJpYXQiOjE1MzQ4NDA4OTAsIm5iZiI6MTUzNDg0MDg5MCwiZXhwIjoxNTY2Mzc2ODkwLCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.BoC6T4cZMnN2bMBPQRG43Ega06s5GfOu8wUMMXeDLL7rTmZS0X7lxmfmpkxPPQfRbuCAHN1wIhQSJD_PWf7GB2_04-dgecYUkptE139UwmTOBS_lvEMhC5OkiwAtOsY8PfYsm91obNRuqY8JF58luhw54tfX7dS1zavUVI_0oE4D1Vr76FpzZlqLCaXSHzws_yN3QVVWfsRgSOmLKH1-qxcrSRaL0tlwJ50PK_i9rABkefvNNTbscjx4tFGIxjo4RGBFyVxnKh8mgTmZOY15rr76DrnK3ZiuopQpKZrFWZSV_1lrN1mcCmOqFSnEahksbIFZbpXhHSBqqMHjE4UGwSRDoIm_JXfNtlUpXvLCfaKmv_zS_MOIPhz4DvQPqbmtXH2qhhuf7n8zzGPfKUHbAZUYwgmt73G8ypUrLXZOIOB8FMfiPhecavLKVdZLQEmQNqHf-lIOWpmGKwt9Qwl5AS8kPj6xlN1BvLtOTb7a9WpYblkrUt62pRqPQUWBLgFlGTSs5UqUWNCypkNxvNTM1pa71UqX2zV-Zy3WDVHhxILsnvU05VN24P_f3egUi8CBxLsvG0oN3Rpbv9cRYWU481RpH08bTM5e8mYAaLLxy21x-CECEfHOo5hzf86LoUkyrTwYZO13a_3RzxKMo0lmh4g4Erpz-0WocwtHCZxRhuY";
        Header header = new Header();
        header.append("Authorization", token);

        Uri uri = Uri.parse(baseUrl);

        final Request.Builder builder = new Request.Builder(this, uri, new Query(new Me()) {
            @Override
            public String getOperationName() {
                return "Me";
            }
        });

        builder.setHeader(header)
                .setTimeout(10)
                .build()
                .enqueue(new Callback() {

                    @Override
                    public void onResponse(String response) {
                        Me me = new GsonBuilder().create().fromJson(response, Me.class);
                        txt.setText("response : " + me.getPreferences().get(0).getType());
                    }

                    @Override
                    public void onFailure() {
                        txt.setText("response failure");
                    }
                });
    }

    // get Mutation Request Builder
    public Request.Builder getMutation() {

        return new Request.Builder(this, Uri.parse(baseUrl), new Mutation() {

            @Override
            public String[] getResponseFields() {
                return new String[]{"id", "pan"};
            }

            @Override
            public String getOperationName() {
                return "CardRestore";
            }

            @Override
            public HashMap<String, ?> getRequestFields() {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", "13");
                return map;
            }
        });
    }
}

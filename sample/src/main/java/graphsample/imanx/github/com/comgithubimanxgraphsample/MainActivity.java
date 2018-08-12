package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.imanx.QLroid.Mutation;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.callback.Callback;
import com.github.imanx.QLroid.request.Header;
import com.github.imanx.QLroid.request.Request;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG_A";

    private static String baseUrl = "http://192.168.66.115/api/graphql";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        baseUrl = "http://91.239.55.205:8080/api/graphql";

        final TextView txt = findViewById(R.id.txt);


        String token  = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjI4MGZiNzlmYTE1NzIyNzI4ZGI4YzJmYTkxZGRkN2MwNmQ1OWZkZGI3YTg2YmEzZTY4ZDkxNjc4NTEyYmE3ZjcyMGFlZTcxMTBjYTBkZjg1In0.eyJhdWQiOiIxIiwianRpIjoiMjgwZmI3OWZhMTU3MjI3MjhkYjhjMmZhOTFkZGQ3YzA2ZDU5ZmRkYjdhODZiYTNlNjhkOTE2Nzg1MTJiYTdmNzIwYWVlNzExMGNhMGRmODUiLCJpYXQiOjE1MzM3MTg0MjAsIm5iZiI6MTUzMzcxODQyMCwiZXhwIjoxNTY1MjU0NDIwLCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.cu4KNY7rnDgXnIiIZX0Odi2u26eKOwVNfy5G35cXzpQ0oD-0ecnBX1sLIXTriteu7sCo5xBB0lm-ZB7KVzHXF2wIEHas5vEXCBe9BF94OQ7DFSQCWhP8ZoZAHx0hlxDtCrfuKVh4KPPc9PrKTG7uI5TDG511cNK769aJ6kC_lRM9tmYRKaU1GI2JrDr7TCoTjVx6mCT0tUKyHbLqr2CMXWzNcQFdTRUkWkFuJUAdrDzNz4Hmeawp4ER9iR-uBshRw-48a1ebSfK5akOUH61ptxBvrvrHvhEyJMQmJ4FIq-rgPTSMVHseXB4tosxqxgNTqgXTkonR0YdfPjDnBqAzymejCGPbZJ5j3xDBAodNIQ4rOq8Nec8DFeruwEgTcFVCBA5BB0NlU_BKIx8BfMI6MW1jx2JWTmHjGUIJpqGYB0ZphdwW0_yqxCeJoWVvdgtoeqDDusNXU6ei4JJmDbXo9RYQe3CoP9zke3rTT0xlegQ-7ufRUgD9cLE1mztu_WaC7PvP_9Abvxj5YCF9dQGF6V1sgY627RDYnGlCYogPr041Vdg7DxlqJsTTdGoTH8a8HrM7knTHF-qfdJztC8_n9az7aNa6VEYWe2v1iTaJugT-uk3NiIiau6EYkJ0yjaaLgkEnsExYEml_JpX9WLztdO2ENRPT3KWHKSjc01J2Va0";
        Header header = new Header();
        header.append("Authorization", token);

        Uri uri = Uri.parse(baseUrl);

        Request.Builder builder = new Request.Builder(this, uri, new Query(new Me()) {
            @Override
            public String getOperationName() {
                return "Me";
            }

        });

        builder.setHeader(header)
                .setTimeout(10)
                .build()
                .enqueue(new Callback<Me>() {
                    @Override
                    public void onResponse(Me response) {
                        txt.setText("response Ok");
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

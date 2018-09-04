package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.github.imanx.QLroid.Mutation;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.argument.Arg;
import com.github.imanx.QLroid.callback.Callback;
import com.github.imanx.QLroid.request.Argument;
import com.github.imanx.QLroid.request.Header;
import com.github.imanx.QLroid.request.Request;

import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG_A";

    private String baseUrl = "http://192.168.66.115/api/graphql/my";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txt = findViewById(R.id.txt);

        baseUrl = "http://91.239.55.205:8080/api/graphql/my";


        Header header = new Header();
        header.append(
                "Authorization",
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImE4ZGU0YjJiZDlkMDQ4Y2E4ZDJjMDQzYmNiMjgzODM1MTJhZGY1N2NjNmZjODI3Njk2ODJjY2U3NmMwMTE5YmZkNzRmMTU3YzdjMzI2NzM2In0.eyJhdWQiOiIxIiwianRpIjoiYThkZTRiMmJkOWQwNDhjYThkMmMwNDNiY2IyODM4MzUxMmFkZjU3Y2M2ZmM4Mjc2OTY4MmNjZTc2YzAxMTliZmQ3NGYxNTdjN2MzMjY3MzYiLCJpYXQiOjE1MzUxODA1MzgsIm5iZiI6MTUzNTE4MDUzOCwiZXhwIjoxNTY2NzE2NTM4LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.OPLiIE5270mMTyt8W4KVnH6nSavuORMC5-JEdK0lDJCx283zDiLnCxh3Fbj35EH6ezvfosSm89vL_-xfJRgAgvdj2erRLvct0Lzj-9PZlbQRXYtw0B8pf2oAVwZG0WJiQhUTH4cTWFv6RmZSUyzYI9EWQJQ1u3ylkbmynoVMox3ZnmubwTFMUHenicI69ipk5GHoBGWAYQ098iV2o4Qu9ge9B4kFd3DsVU8kkU_ASPzg5YM61eHOeOho99GVFpn6roEEvCNiRALIihmZfCFjf90HoIbXV_gQhJc_mMnlSs2FWHRtNM0VLplnd_1AttyiR9Qi0LgHnqy0YcVNz7x9rBpPYXrxvLauhqRermxAF1qJO8VZTHIwifHJ5tpx8UgLHVzrpU2M4e9JDm0QiAs0dS8dP2tW_uUEEbVokumws2BefuvvPzQ1H6RyObiTKOfMpLEXCul8OmGfzeKIbQwhJwedUpS5TmN9gIgm7QIEE0JseMojJOLqBF8EdpQ0hEEWt7gZQhUiJzbdCCh9Pj8x8dghEikdBtYGiGHnhnwxUA3C62aebnVYrGnEGaNQPEaGUJs3v9LnQPAnxzXLyMirJSh0CHlAjgJ7d2Sw8hmPsCtaNmJhrqSmShzE3m1JqS0wanxs7gT5C_UCUQkTLo2juIaVbUS3PEwsXIwuXqCMqhQ");

        getMutation().setHeader(header)
                .setTimeout(10)
                .build()
                .enqueue(new Callback() {
                    @Override
                    public void onResponse(String response) {
                        txt.setText("response : " + response);
                    }

                    @Override
                    public void onFailure() {
                        txt.setText("response failure");
                    }
                });
    }

    public Request.Builder getQuery(){
        return new Request.Builder(this, Uri.parse(baseUrl), new Query() {
            @Nullable
            @Override
            public String getOperationName() {
                return "Cards";
            }

            @Override
            public String[] getResponseFields() {
                return new String[]{"id","pan"};
            }
        });
    }

    // get Mutation Request Builder
    public Request.Builder getMutation() {

        return new Request.Builder(this, Uri.parse(baseUrl), new Mutation() {

            @Override
            public String getOperationName() {
                return "CardAdd";
            }

            @Override
            public String[] getResponseFields() {
                return new String[]{"id"};
            }

            @Override
            public Argument getArgument() {
                Argument argument = new Argument();
                argument.add(new Arg("pan", "6037997228928280", MaskedPan.class)
                        , new Arg("expired_at", "2020-02-00", Date.class));
                return argument;
            }
        });
    }
}

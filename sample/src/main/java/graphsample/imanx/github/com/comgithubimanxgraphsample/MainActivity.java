package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.callback.Callback;
import com.github.imanx.QLroid.request.Header;
import com.github.imanx.QLroid.request.Request;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private String baseUrl = "https://plus.zarinpal.com/api/graphql/my";
    private String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImE3MWNmZjU1YWI0NGY5YjY0M2Q4NmY1NTYzNWRlZjI5MzMzODVkYzU3OGFkMGJkZTg2Mjk0MjQzMmQxYmU4MzEzZDgxOTYwMjgwNjRiZjRmIn0.eyJhdWQiOiIxIiwianRpIjoiYTcxY2ZmNTVhYjQ0ZjliNjQzZDg2ZjU1NjM1ZGVmMjkzMzM4NWRjNTc4YWQwYmRlODYyOTQyNDMyZDFiZTgzMTNkODE5NjAyODA2NGJmNGYiLCJpYXQiOjE1Mzg5ODU2MDQsIm5iZiI6MTUzODk4NTYwNCwiZXhwIjoxNTcwNTIxNjAxLCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.yvCi-idUpafQvp2DBLcFydKM2zV92xgaD1IPPKZrwcYGj58rnhYx_vR66iKzFaHTgSH9UgnyJ-uC0lxVIgE1AvTu_C1dCQRRoICVB2EXVCi-sBEojs4GOqPoMRszj1eeqZwLT85iUiXEXWaNU7GLskBWtGcotq24IbtjK3v67vkl62pzhBxsnNzZX8bKIc9ahKYDfydl8DHuFoZMW4Js3bacI9fsD3Mv_416tkPaRZK93uViq9ifYsWyHhfpR4m-zcdcbdCGymTOLDKorxAkRuB8qqdHcSSTPG-wB1pq-6vyJ0v8LVUnPnkzwgAmiwGipzMJBlz3HRFkH9g2ASEyuo-swcROpKVU-RvysYh-C0z3ss6c6lC85MR3xH30bzgeWN88YrNLw90OK3lesVgs-Xs1VqZbdUaO2p2Y0eoMjOgHyE9hGkFnxEcfCnGwpvKbe0b2h5CdjZepcfF2-qveVGBDnSkh1e3jztBGjcQmtoN8cBNgxq1TPBpBx_TTFXYffJUMoFj1K4iIAvuhTMw0In8nzIwD3o0uXENKelJzE3uEKyICZ1dg3bQZdz3sGRoPADqPnzOJQ1FbvsL3OQtrH9Qvkfv-U27w5KKUYb7r4-OxY4eiwpeNhIWe49VtptfbNYznfH4hqEdny9Ak5qgIXnLBvhanEPXE24nyYT3HonI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Header header = new Header();
        header.append("Authorization", token);
        header.append("Accept", "application/json");

        getQuery().setHeader(header)
                .setTimeout(10)
                .build()
                .enqueue(new Callback() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, "onResponse: " + response);
                    }

                    @Override
                    public void onFailure(int httpCode, String data) {
                        Log.i(TAG, "onFailure: " + httpCode + " ||  " + data);
                    }
                });

    }

    public Request.Builder getQuery() {
        return new Request.Builder(this, Uri.parse(baseUrl), new Query(new Me()) {
            @Nullable
            @Override
            public String getOperationName() {
                return "Me";
            }
        });
    }
}

package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.imanx.QLroid.callback.Callback;
import com.github.imanx.QLroid.request.Header;
import com.github.imanx.QLroid.Mutation;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.request.Request;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG_A";

    private static final String baseUrl = "http://192.168.66.115/api/graphql";
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImMxMTZhMzIyNGYxOTFhMjcwMjFjZDU2ZDZmZTBiZjkyNjJmOTBkNWEzNGZjMGRlZjE2MzI1YjRmNjdkYTI4NWFiYWViNjVjYjM0NGQ1MGMzIn0.eyJhdWQiOiIxIiwianRpIjoiYzExNmEzMjI0ZjE5MWEyNzAyMWNkNTZkNmZlMGJmOTI2MmY5MGQ1YTM0ZmMwZGVmMTYzMjViNGY2N2RhMjg1YWJhZWI2NWNiMzQ0ZDUwYzMiLCJpYXQiOjE1MzMzNjIxMzAsIm5iZiI6MTUzMzM2MjEzMCwiZXhwIjoxNTY0ODk4MTMwLCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.fLR7YlfFWis7y3HHPS8svK3hPeTkJ3PoFbFQR0MCD7Ids764OyUgqZeGxp8ASbEktSvqAEpcAOr9zO9DgL71HR3a6Pgd_Nrlv3RVV1CEzkAOjdBKGFSLeDSwT8cnCaOrYMrUjzXJV1CMjpDtXuR1CNPOVfVkKL9l6sXrbzoHuyXDeharv5h0DkkdtEfFP3_UhdGYHDjDfx4bDcBDKLWa7zdwMsJ9BnyLPVIEXlfUDlHkUjXbX17FqIN-XKvWuzKnRSr82ic6dhQABLmRsQZGa-1CgovP2rRK1_RNMwNoVQnAtMH55HFYC2Hjsu4RsSkThavpkUCFjYCU1lTNDi8qt1AzUGrIanX69dCYhJ946pyml7eNUDqA4TjFq3DuY7Kxa6UqauAJ3Jq4HoJ_ek7F9CGaFsvBg3Q7GgIwew46UuiKiChp4R0_0D0k5zNDmgkA_tKYx9dxtB6TXsX7v7dMtQz6wKtg8FqPfR6MNeAPGZqBmvvYNQ-CrzZWB69nKZCg6iDi5rmbCZXBiCWBk-MGH9VeO7vuSOaKlMJG-ccqqv89bYg3DQKAaJEWR-PSJKCfTvp6kH6pCn9X-gxF2fWsYionBHWwAzagMpRek_odbeQnAgjMpzKYZv8KwCVtq7sGB209XnWdfk17fvZJrknmK_5taArM0AesbBeUuLRKTO4";

        Header header = new Header();
        header.append("Authorization", token);

        this.uri = Uri.parse(baseUrl);

        getQuery()
                .setHeader(header)
                .setTimeout(10)
                .build()
                .enqueue(new Callback() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, "onResponse: " + response);
                    }

                    @Override
                    public void onFailure() {
                        Log.i(TAG, "onFailure: ");
                    }
                });

    }

    // get Mutation Request Builder
    public Request.Builder getMutation() {

        return new Request.Builder(this, uri, new Mutation() {

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
                map.put("id", "7");
                return map;
            }
        });
    }

    // get Query Request Builder
    public Request.Builder getQuery() {
        return new Request.Builder(this, uri, new Query(new Me()) {
            @Override
            public String getOperationName() {
                return "Me";
            }
        });
    }
}

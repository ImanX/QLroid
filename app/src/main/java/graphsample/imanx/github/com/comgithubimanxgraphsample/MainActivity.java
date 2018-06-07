package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.imanx.QLroid.Callback;
import com.github.imanx.QLroid.Header;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.Request;

public class MainActivity extends AppCompatActivity implements Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Header header = new Header();
        header.append(new Header.Entity("Authorization", "bearer 89c084087df48df1d2d42e561bcd941936743926"));

        new Request.Builder(this, Uri.parse("https://api.github.com/graphql"), new Query() {
            @Override
            public String[] getResponseFields() {
                return new String[]{"bio", "company"};
            }

            @Override
            public String getOperationName() {
                return "viewer";
            }
        })
                .setHeader(header)
                .setTimeout(10)
                .build().enqueue(this);

    }


    @Override
    public void onResponse(String response) {

    }

    @Override
    public void onFailure() {

    }
}

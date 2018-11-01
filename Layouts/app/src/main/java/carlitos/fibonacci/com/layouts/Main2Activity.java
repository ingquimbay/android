package carlitos.fibonacci.com.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        WebView myWebView = (WebView)
        findViewById(R.id.web_view_layout);
        myWebView.loadUrl("http://www.javeriana.edu.co");
    }
}

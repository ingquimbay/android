package carlitos.fibonacci.com.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView textView = (TextView) findViewById(R.id.text_on_frame);
        textView.setText(getIntent().getStringExtra("nombre") + " " + getIntent().getStringExtra("educacion"));
    }
}

package carlitos.fibonacci.com.fibonacci;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final TextView value = (TextView) findViewById(R.id.textView);
        //final ScrollView scroll = (ScrollView) findViewById(R.id.scrollV);
        final EditText number = (EditText) findViewById(R.id.editNumber);
        final EditText number2 = (EditText) findViewById(R.id.editNumber2);
        Button fibButton = (Button) findViewById(R.id.fibbutton);
        Button facButton = (Button) findViewById(R.id.facbutton);
        Button paisesButton = (Button) findViewById(R.id.buttonPaises);
        final TextView txt1 = (TextView) findViewById(R.id.ts1);
        final TextView txt2 = (TextView) findViewById(R.id.ts2);
        final String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());

        fibButton.setOnClickListener(new View.OnClickListener() {
            //long num = 0;
            @Override
            public void onClick(View view) {
                //num++;
                //value.append(fibonacci(num)+"\n");
                //scroll.smoothScrollTo(0,value.getBottom());
                //DateFormat dateFormat = new DateFormat("yyyy-MM-dd HH:mm:ss");

                long num = Long.parseLong(number.getText().toString());
                Intent intent = new Intent(view.getContext(), Main2Activity.class);
                intent.putExtra("fib", num);
                startActivity(intent);
                number.getText().clear();
                txt1.setText(currentDateTime);
            }


        });

        facButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num2 = Integer.parseInt(number2.getText().toString());
                Intent intent = new Intent(view.getContext(), Main3Activity.class);
                intent.putExtra("fac", num2);
                startActivity(intent);
                number2.getText().clear();
                txt2.setText(currentDateTime);

            }
        });

        paisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Main4Activity.class);
                startActivity(intent);
            }
        });
    }
}

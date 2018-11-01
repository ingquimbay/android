package quimbay.com.tallercm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RangeActivity extends AppCompatActivity {

    EditText newNumber;
    Button accept;
    int newRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);

        newNumber = (EditText) findViewById(R.id.rangeNumber);
        accept = (Button) findViewById(R.id.acceptButton);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!newNumber.getText().toString().isEmpty()) {
                    newRange = Integer.parseInt(newNumber.getText().toString());
                    Intent i = new Intent(view.getContext(), GuessActivity.class);
                    i.putExtra("newRange",newRange);
                    setResult(RESULT_OK,i);
                    finish();
                } else {
                    Toast.makeText(view.getContext(), "Debe digitar un numero.", Toast.LENGTH_LONG).show();
                } newNumber.getText().clear();
            }
        });
    }
}

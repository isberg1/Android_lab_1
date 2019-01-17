package android.lab_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transfer);


        Bundle bundle = getIntent().getExtras();
        int balance = bundle.getInt("balance");
        System.out.println("\n\naaaaaaaaaaaaa \n" + balance + "\n\naaaaaaaaaaa" );


        TextView textView = findViewById(R.id.main_transfer_textView);
        textView.setText("TransferActivity:  " + Integer.toString(balance));


        Spinner spinner = findViewById(R.id.Transfer_friend_dropdown_spinner);

        String[] friends = bundle.getStringArray("friends");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, friends);

        spinner.setAdapter(adapter);
    }
}

package android.lab_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TransactionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transactions);


        Bundle bundle = getIntent().getExtras();
        int balance = bundle.getInt("balance");
        System.out.println("\n\naaaaaaaaaaaaa \n" + balance + "\n\naaaaaaaaaaa" );
        TextView textView = findViewById(R.id.Transactions_Balance_textView);


        textView.setText("TransactionActivity:  " + Integer.toString(balance));


    }
}

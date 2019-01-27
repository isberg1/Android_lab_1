package android.lab_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TransactionsActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transactions);

        // get intent form calling activity
        Bundle bundle = getIntent().getExtras();
        int balance = bundle.getInt(MainActivity.balanceKey);

        this.textView = findViewById(R.id.Transactions_Balance_textView);
        textView.setText("TransactionActivity:  " /*+ MainActivity.lblBalanceToString(balance)*/);


    }
}

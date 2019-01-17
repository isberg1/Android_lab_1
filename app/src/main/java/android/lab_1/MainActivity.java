package android.lab_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static Integer lbl_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // make random int from 90 to 110
        Random random = new Random();
        lbl_balance = random.nextInt(111 - 90) + 90;
        String str = lbl_balance.toString();

        // set text field value of MainActivity_intBalance_TextView
        TextView tv = findViewById(R.id.MainActivity_intBalance_TextView);
        tv.setText(str);
    }


    public void transactions(View view) {
        Button btn = findViewById(R.id.mainActivity_Transactions_button);

        Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);

        intent.putExtra("balance", lbl_balance);

        startActivity(intent);



    }

    public void Transfer(View view) {
        Button btn = findViewById(R.id.mainActivity_Transfer_button);

        Intent intent = new Intent(MainActivity.this, TransferActivity.class);

        intent.putExtra("balance", lbl_balance);

        String[] friends = {"homer","marge","bart","lisa", "moe", "mr.Burns" };

        intent.putExtra("friends",friends);

        startActivity(intent);

    }
}

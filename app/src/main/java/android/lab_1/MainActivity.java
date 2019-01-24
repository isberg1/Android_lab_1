package android.lab_1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private  Integer lbl_balance;
    private String[] friends;


    private final int PICK_TRANSFER_REQUEST = 1;  // The request code for Transfer
    private final int PICK_TRANSACTION_REQUEST = 2;  // The request code for Transaction

    public final static String balanceKey ="balance";
    public final static String friendsKey ="friends";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // make random int from 90 to 110
        Random random = new Random();
        this.lbl_balance = (random.nextInt(111 - 90) + 90) * 100;

        this.friends = getFriends();

        // set text field value of MainActivity_intBalance_TextView
        this.textView = findViewById(R.id.MainActivity_intBalance_TextView);
        this.textView.setText(lblBalanceToString(this.lbl_balance));


    }

    private String[] getFriends() {
        String[] friends = {"homer","marge","bart","lisa", "maggi", "moe", "mr.Burns" };
        return friends;
    }

    public static String lblBalanceToString (Integer balance) {
        return   String.format("%.02f",(balance.floatValue() / 100));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PICK_TRANSFER_REQUEST      :
                transferResult(requestCode, data);
                break;
            case PICK_TRANSACTION_REQUEST   :
                transactionResult(requestCode, data);
                break;
            default: return;
        }

    }
// todo finish making 
    private void transferResult(int requestCode, Intent data) {

        if (requestCode == RESULT_CANCELED ) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle =data.getExtras();

        String string = bundle.getString(balanceKey);


    }


    private void transactionResult(int requestCode, Intent data) {
    }


    public void transactions(View view) {
        // make intent and set data
        Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
        intent.putExtra(balanceKey, lbl_balance);

        startActivity(intent);



    }

    public void Transfer(View view) {
        // make intent and set data
        Intent intent = new Intent(MainActivity.this, TransferActivity.class);
        intent.putExtra(balanceKey, this.lbl_balance);
        intent.putExtra(friendsKey,this.friends);

        startActivity(intent);

    }



    private class TransactionEvent  {
        String timeStamp;
        String resipient;
        Integer transferAmount;
        Integer newBalance;

         @Override
        public String toString() {
             String separator = " | ";
             String formattedString = "\n";
             formattedString += timeStamp;
             formattedString += separator;
             formattedString += resipient;
             formattedString += separator;
             formattedString += MainActivity.lblBalanceToString(transferAmount);
             formattedString += separator;
             formattedString += MainActivity.lblBalanceToString(newBalance);

            return formattedString;
        }
    }



}

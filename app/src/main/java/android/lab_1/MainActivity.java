package android.lab_1;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private  Integer lbl_balance;
    private String[] friends;
    private DataBase mDB;


    private final int PICK_TRANSFER_REQUEST = 1;  // The request code for Transfer
    private final int PICK_TRANSACTION_REQUEST = 2;  // The request code for Transaction

    public final static String DbKey ="DB";
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

        Integer temp = (random.nextInt(111 - 90) + 90) * 100;

        //configure DB connection
        mDB =new DataBase(temp);

        setFriends();

        // set text field value of MainActivity_intBalance_TextView
        this.textView = findViewById(R.id.MainActivity_intBalance_TextView);
        this.textView.setText(lblBalanceToString(mDB.getLbl_balance()));
    }

    @Override
    protected void onResume() {

        super.onResume();
        this.textView.setText(lblBalanceToString(mDB.getLbl_balance()));

    }

    private void setFriends() {
        String[] friends = {"homer","marge","bart","lisa", "maggi", "moe", "mr.Burns" };

        for (String temp : friends) {
            mDB.addFriend(temp);
        }
    }

    public String lblBalanceToString (Integer value) {
        return   String.format("%.02f",(value.floatValue() / 100));
    }

    // todo copier den returnerende DB til mainact DB
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
// todo implementer
    private void transferResult(int requestCode, Intent data) {

        if (requestCode == RESULT_CANCELED ) {
            return;
        }




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
        intent.putExtra(DbKey, mDB);
        // todo change to startActivityForResult in order to copy new DB
        startActivity(intent);

    }



    private class TransactionEvent  {
        String timeStamp;
        String recipient;
        Integer transferAmount;
        Integer newBalance;

         @Override
        public String toString() {
             String separator = " | ";
             String formattedString = "\n";
             formattedString += timeStamp;
             formattedString += separator;
             formattedString += recipient;
             formattedString += separator;
             formattedString += lblBalanceToString(transferAmount);
             formattedString += separator;
             formattedString += lblBalanceToString(newBalance);

            return formattedString;
        }
    }



}

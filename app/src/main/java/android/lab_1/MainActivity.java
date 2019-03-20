package android.lab_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DataBase mDB;
    private final int PICK_TRANSFER_REQUEST = 1;  // The request code for Transfer
    public final static String DbKey ="DB";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // make random int from 90 to 110
        Random random = new Random();
        Integer temp = (random.nextInt(111 - 90) + 90) * 100;

        //configure DB connection
        String username = "mr.Burns";
        mDB =new DataBase(temp, username);

        // sett list of friends in mDB
        setFriends();

        // set text field value of MainActivity_intBalance_TextView
        this.textView = findViewById(R.id.MainActivity_intBalance_TextView);
        this.textView.setText(lblBalanceToString(mDB.getLbl_balance()));
    }

    @Override
    protected void onResume() {
        // update text view with new value
        super.onResume();
        this.textView.setText(lblBalanceToString(mDB.getLbl_balance()));

    }
    // add friends to mDB
    private void setFriends() {
        String[] friends = {"Homer","Marge","Bart","Lisa", "Maggi", "Moe", "Apo" };

        for (String temp : friends) {
            mDB.addFriend(temp);
        }
    }
    // convert form Integer to String
    public String lblBalanceToString (Integer value) {
        return   String.format("%.02f",(value.floatValue() / 100));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_TRANSFER_REQUEST) {
            transferResult(resultCode, data);
        }



    }
    // process the result from Transfer Activity
    private void transferResult(int resultCode, Intent data) {

        if (resultCode == RESULT_CANCELED ) {
            return;
        }

        // get parameters form Transfer Activity
        Bundle bundle = data.getExtras();
        // update mDB whit new state
        this.mDB = (DataBase)bundle.getSerializable(MainActivity.DbKey);

    }
    // transfer to Transaction history Activity
    public void transactions(View view) {
        // make intent and set data
        Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
        intent.putExtra(DbKey, mDB);

        startActivity(intent);

    }
    // transfer to Transfer Activity
    public void Transfer(View view) {
        // make intent and set data
        Intent intent = new Intent(MainActivity.this, TransferActivity.class);
        intent.putExtra(DbKey, mDB);

        startActivityForResult(intent, PICK_TRANSFER_REQUEST);

    }

    // ensure that changing orientation does not restart the app
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(MainActivity.DbKey, mDB);
    }
    // ensure that changing orientation does not restart the app
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        this.mDB = (DataBase) savedInstanceState.getSerializable(MainActivity.DbKey);
    }
}

package android.lab_1;

import android.arch.lifecycle.Lifecycle;
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
        //this.lbl_balance = (random.nextInt(111 - 90) + 90) * 100;

        Integer temp = (random.nextInt(111 - 90) + 90) * 100;

        //configure DB connection
        String username = "mr.Burns";
        mDB =new DataBase(temp, username);

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
        String[] friends = {"Homer","Marge","Bart","Lisa", "Maggi", "Moe", "Apo" };

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
                transferResult(resultCode, data);
                break;
            default: return;
        }

    }
// todo implementer
    private void transferResult(int resultCode, Intent data) {

        if (resultCode == RESULT_CANCELED ) {
            return;
        }

        Bundle bundle = data.getExtras(); //getIntent().getExtras();

        this.mDB = (DataBase)bundle.getSerializable(MainActivity.DbKey);

    }

    public void transactions(View view) {
        // make intent and set data
        Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
        intent.putExtra(DbKey, mDB);

        startActivity(intent);

    }

    public void Transfer(View view) {
        // make intent and set data
        Intent intent = new Intent(MainActivity.this, TransferActivity.class);
        intent.putExtra(DbKey, mDB);
        // todo change to startActivityForResult in order to copy new DB
//        startActivity(intent);
        startActivityForResult(intent, PICK_TRANSFER_REQUEST);

    }


    // TODO pressing the normal android "back button", quits the app.

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(MainActivity.DbKey, mDB);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        this.mDB = (DataBase) savedInstanceState.getSerializable(MainActivity.DbKey);
    }
}

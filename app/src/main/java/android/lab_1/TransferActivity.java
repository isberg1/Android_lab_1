package android.lab_1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TransferActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textView;
    private TextView mLblAmountCheck;
    private DataBase mDB;

    private EditText txt_amount;
    private Button btn_pay;
    private Spinner spinner;

    private int flag;
    private int FLAG_OK=1;
    private int FLAG_NOT_OK = 0;

    private Integer mAmountToTransfer = 0;
    private Friend mRecipient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transfer);

        // get data from intent
        Bundle bundle = getIntent().getExtras();
        mDB = (DataBase)bundle.getSerializable(MainActivity.DbKey);

        setUp();

        // get Gui Field for debugging todo remove eventually
        this.textView = findViewById(R.id.main_transfer_textView);
       debug();


      //  checks to see if text has been entered into the field txt_amount
        txt_amount.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i == EditorInfo.IME_ACTION_DONE){
                if (validateTxtAmount()) {
                    correctValueResponse();
                } else {
                    incorrectValueResponse();
                }
            }
            return false;
        });

    }

    public void correctValueResponse(){
        btn_pay.setEnabled(true);
        String toTransfer = this.txt_amount.getText().toString();
        this.mAmountToTransfer = stringToFormattedInteger(toTransfer);
        this.txt_amount.setCursorVisible(false);
        this.txt_amount.getText().clear();
        this.txt_amount.setHint("value to be transferred:\t" + toTransfer) ;
        this.mLblAmountCheck.setText("");
    }

    public void incorrectValueResponse(){
        btn_pay.setEnabled(false);
        String error ="amount must be larger than 0\n and smaller or equal to ";
        error += mDB.lblBalanceToFormattedString();
        this.mLblAmountCheck.setText( error);
        this.mLblAmountCheck.setTextColor(Color.RED);
    }

    private Integer stringToFormattedInteger(String toTransfer) {

        Integer intAmount;

        try {
            String temp =  String.format(java.util.Locale.US,"%.02f",Float.valueOf(toTransfer));
            Float floatAmount = Float.valueOf(temp) * 100;
            intAmount = floatAmount.intValue();
        } catch (NumberFormatException e) {
            // todo handle exception better
            e.printStackTrace();
            this.mLblAmountCheck.setText("Internal error, try again ");
            return 0;
        }
        return intAmount;
    }

    public void setUp() {

        // get Gui Field for dropdown menu
        this.spinner = findViewById(R.id.Transfer_friend_dropdown_spinner);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, mDB.friendsAsStringArray());
        this.spinner.setAdapter(adapter);
        this.spinner.setOnItemSelectedListener(this);

        // get Gui Field
        this.txt_amount = findViewById(R.id.txt_amount);
        this.mLblAmountCheck = findViewById(R.id.lbl_amount_check);

        // get Gui Field
        this.btn_pay = findViewById(R.id.btn_pay);
        this.btn_pay.setEnabled(false);
    }

// todo return value to calling activity
    public void btn_pay(View view) {

        if (this.mRecipient == null  || this.mAmountToTransfer == null){
            this.mLblAmountCheck.setText("Missing recipient or transfer amount");
        }
        if (!mDB.newTransaction(this.mRecipient, this.mAmountToTransfer)){
            this.mLblAmountCheck.setText("Internal error, try again!");
        }

        Intent data = new Intent(TransferActivity.this, MainActivity.class);
        data.putExtra(MainActivity.DbKey,this.mDB);
        setResult(Activity.RESULT_OK,data);
        finish();
    }

    private boolean validateTxtAmount(){

        if (this.txt_amount.getText() == null) {
            return false;
        }

        Integer subtract;

        try {
            String temp = this.txt_amount.getText().toString();

            float floatTemp =  Float.valueOf(temp) * 100;
            subtract = (int)floatTemp;
        } catch (NumberFormatException e) {
            //todo handle exception better
            e.printStackTrace();
            return false;
        }
        // if number is to big or to small
        if (subtract > mDB.getLbl_balance() || subtract == 0) {
            return false;
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = (String) this.spinner.getSelectedItem();

        if (mDB.validFriend(s)){


            s.replaceAll(" ","");
            this.mRecipient = mDB.copyFriend(s);
            return;
        }
        this.mRecipient = null;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        this.mRecipient = null;

    }

    public void debug(){
        this.textView.setText("in account  " + mDB.lblBalanceToFormattedString()+ " to transfer " + mAmountToTransfer);
    }
}

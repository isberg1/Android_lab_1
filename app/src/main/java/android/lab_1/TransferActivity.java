package android.lab_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TransferActivity extends AppCompatActivity {

    private TextView textView;
    private Integer lbl_balance;
    private String[] friends;
    private EditText txt_amount;
    private Button btn_pay;
    private Spinner spinner;

    private int flag;
    private int FLAG_OK=1;
    private int FLAG_NOT_OK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transfer);

        // get data from intent
        Bundle bundle = getIntent().getExtras();
        this.lbl_balance = bundle.getInt(MainActivity.balanceKey);

        // get Gui Field for debugging todo remove eventually
        this.textView = findViewById(R.id.main_transfer_textView);
        this.textView.setText("TransferActivity:  " + MainActivity.lblBalanceToString(lbl_balance));

        // get Gui Field for dropdown menu
        this.spinner = findViewById(R.id.Transfer_friend_dropdown_spinner);
        this.friends = bundle.getStringArray(MainActivity.friendsKey);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, this.friends);
        this.spinner.setAdapter(adapter);

        // get Gui Field
        this.txt_amount = findViewById(R.id.txt_amount);

        // get Gui Field
        this.btn_pay = findViewById(R.id.btn_pay);
        this.btn_pay.setEnabled(false);
        this.flag = FLAG_NOT_OK;

        // todo add check to see if value has change several times
      //  checks to see if text has been entered into the field txt_amount
        txt_amount.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i== EditorInfo.IME_ACTION_DONE){
                if (validateTxtAmount()) {
                    btn_pay.setEnabled(true);
                }
            }
            return false;
        });

    }
// todo return value to calling activity
    public void btn_pay(View view) {

        Button button = findViewById(R.id.btn_pay);


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
        if (subtract > this.lbl_balance || subtract == 0) {
            return false;
        }

        return true;
    }

}

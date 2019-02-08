package android.lab_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class TransactionsActivity extends AppCompatActivity {

    private DataBase mDB;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> list;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transactions);

        // get intent form calling activity
        Bundle bundle = getIntent().getExtras();
        mDB = (DataBase)bundle.getSerializable(MainActivity.DbKey);
        this.list = new ArrayList();
        // initialize gui elements
        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        transactionHistoryToString();
        adapter = new RecyclerAdapter(mDB.getHistory(), this);
        recyclerView.setAdapter(adapter);

    }
    // extracts a String list of all transactions form mDB
    private void transactionHistoryToString() {

        if (this.mDB == null){
            Log.d("DB", "null");
            return;
        }
        List<TransactionEvent> tt= this.mDB.getHistory();
        for (TransactionEvent te : tt){
            this.list.add(te.toString());
        }

    }
}

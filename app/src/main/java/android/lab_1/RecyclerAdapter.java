package android.lab_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder>  {
    public final String TAG = "RecyclerAdapter";
    private List<TransactionEvent> list;

    private  Context context;

    public RecyclerAdapter(List<TransactionEvent> list,  Context context1) {
        Log.d(TAG, "RecyclerAdapter");
        this.list = list;
        this.context = context1;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder");
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view_layout, viewGroup, false);

        return new myViewHolder(textView, context, this.list);
    }

    @Override
    public void onBindViewHolder(myViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder");
        viewHolder.textView.setText(list.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements /*View.OnClickListener,*/ View.OnLongClickListener {
        public final String TAG = "myViewHolder";
        TextView textView;
        Context context;
        List<TransactionEvent> list;


        public myViewHolder(TextView itemView, Context context1, List<TransactionEvent> ls) {
            super(itemView);
            Log.d(TAG, "myViewHolder");
            this.textView = itemView;
            this.context = context1;
            this.list = ls;
            itemView.setOnLongClickListener(this);
        }


        @Override
        public boolean onLongClick(View v) {
            Log.d(TAG, "onLongClick");
            int position = getAdapterPosition();
            String temp = this.list.get(position).shortToString();

            Toast.makeText(v.getContext(),temp, Toast.LENGTH_LONG).show();
            return true;
        }
    }
}

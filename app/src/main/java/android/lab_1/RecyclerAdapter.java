package android.lab_1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewholder> {

    private List<String> list;

    public RecyclerAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view_layout, viewGroup, false);

        return new myViewholder(textView);
    }

    @Override
    public void onBindViewHolder(myViewholder viewHolder, int i) {
        viewHolder.textView.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class myViewholder extends RecyclerView.ViewHolder {
        TextView textView;

        public myViewholder(TextView itemView) {
            super(itemView);
            textView = itemView;
        }
    }
}

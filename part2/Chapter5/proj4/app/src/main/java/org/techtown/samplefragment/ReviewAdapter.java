package org.techtown.proj4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    public static ArrayList<Review> items = new ArrayList<Review>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.review_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Review item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView; // 사용자 ID
        TextView textView2; // 리뷰 시간
        TextView textView3; // 리뷰 내용 || 별점
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.UserId);
            textView2 = itemView.findViewById(R.id.ReviewTime);
            textView3 = itemView.findViewById(R.id.ReviewContent);

        }
        public void setItem(Review item){
            textView.setText(item.getID());
            textView2.setText(item.getTime());
            textView3.setText(item.getContent());
        }
    }

    public void addItem(Review item){
        items.add(item);
    }

    public void setItems(ArrayList<Review> items){
        this.items = items;
    }

    public Review getItem(int position){
        return items.get(position);
    }
    public void setItem(int position, Review item){
        items.set(position, item);
    }
}

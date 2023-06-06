package com.example.morningcity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ParseItemAdapter extends RecyclerView.Adapter<ParseItemAdapter.ViewHolder> {

    private ArrayList<ParseItemModel> parseItemModelArrayList;

    private AdapterView.OnItemClickListener itemClickListener;


    private Context context;

    public ParseItemAdapter(ArrayList<ParseItemModel> parseItemModelArrayList, Context context) {
        this.parseItemModelArrayList = parseItemModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseItemAdapter.ViewHolder holder, int position) {
        ParseItemModel parseItemModel = parseItemModelArrayList.get(position);
        holder.title_txt.setText(parseItemModel.getTitle());
        holder.time_txt.setText(parseItemModel.getDate());
        Glide.with(context)
                .load(parseItemModel.getImageLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return parseItemModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView title_txt, time_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_id);
            title_txt = itemView.findViewById(R.id.Title_txt_id);
            time_txt = itemView.findViewById(R.id.Time_txt_id);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // 获取相应位置的ParseItemModel对象
                        ParseItemModel parseItem = parseItemModelArrayList.get(position);
                        // 获取要跳转的网址
                        String url = parseItem.getWebsiteUrl();
                        // 执行跳转操作，可以使用Intent打开网址或其他操作
                        // 示例代码：
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra("url", url);
                        context.startActivity(intent);
                    }
                }

            });

            //@Override
            //public void onClick (View v){



        }

        @Override
        public void onClick(View v) {

        }
    }
}

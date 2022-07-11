package com.example.readnewrss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NewsItem> productList;

    public NewsItemAdapter(Context context, int layout, List<NewsItem> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);
            viewHolder.txtTieuDe = view.findViewById(R.id.txtTieuDe);
            viewHolder.txtDesc = view.findViewById(R.id.txtDes);
            viewHolder.imgView = view.findViewById(R.id.imgNews);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //gán giá trị
        NewsItem newsItem = productList.get(i);
        viewHolder.txtTieuDe.setText(newsItem.getTieuDe());
        viewHolder.txtDesc.setText(newsItem.getMota());
        Picasso.get()
                .load(newsItem.getImg())
                .fit()
                .into(viewHolder.imgView);
        return view;
    }
    class ViewHolder{
        TextView txtTieuDe;
        TextView txtDesc;
        ImageView imgView;
    }

}

package com.zhijinying.lepaiyizu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.bean.ZXDataInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuying on 2016/9/5.
 */
public class ZXAdapter extends BaseAdapter {

    private List<ZXDataInfo> list;

    public ZXAdapter(ArrayList<ZXDataInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_zx, null);

            holder = new ViewHolder();

            holder.tvTitle = (TextView) convertView.findViewById(R.id.textView);
            holder.iPic = (ImageView) convertView.findViewById(R.id.imagView);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ZXDataInfo dataInfo = list.get(position);

        holder.tvTitle.setText(dataInfo.getTitle());
        Glide.with(parent.getContext())
                .load(dataInfo.getPic_url())
                .placeholder(R.drawable.bg_b)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.iPic);

        return convertView;
    }

    class ViewHolder {
        TextView tvTitle;
        ImageView iPic;
    }
}

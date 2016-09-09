package com.zhijinying.lepaiyizu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.bean.SZDataInfo;

import java.util.List;

/**
 * Created by liuying on 2016/9/5.
 */
public class SZAdapter extends BaseAdapter {

    private List<SZDataInfo> list;
    private Context context;

    public SZAdapter(Context context, List<SZDataInfo> list) {
        this.context = context;
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

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sc, null);

            holder = new ViewHolder();

            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvSCTitle);
            holder.tvWebUrl = (TextView) convertView.findViewById(R.id.tvSCHint);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SZDataInfo dataInfo = list.get(position);

        holder.tvTitle.setText(dataInfo.getTitle());
        holder.tvWebUrl.setText(dataInfo.getWeb_url());

        return convertView;
    }

    class ViewHolder {
        TextView tvTitle, tvWebUrl;
    }
}

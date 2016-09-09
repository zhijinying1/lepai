package com.zhijinying.lepaiyizu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.bean.JXDataInfo;

import java.util.List;

/**
 * Created by liuying on 2016/9/4.
 */
public class JXAdapter extends BaseAdapter {

    private List<JXDataInfo> list;
    private Context context;

    public JXAdapter(Context context, List<JXDataInfo> list) {
        this.list = list;
        this.context = context;
//        notifyDataSetChanged();
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 5 == 0 ? 0 : 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_jx1, null);

            holder = new ViewHolder();

            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.iPic = (ImageView) convertView.findViewById(R.id.iPic);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        JXDataInfo dataInfo = list.get(position);

        holder.tvTitle.setText(dataInfo.getTitle());
        holder.tvDate.setText(dataInfo.getDate());
        final String image = dataInfo.getPic_url();
        Glide.with(context)
                .load(image)
                .placeholder(R.drawable.bg_b)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.iPic);

        return convertView;
    }

    class ViewHolder {
        TextView tvTitle, tvDate;
        ImageView iPic;
    }
}

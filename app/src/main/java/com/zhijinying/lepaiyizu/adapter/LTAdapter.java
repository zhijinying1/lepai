package com.zhijinying.lepaiyizu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhijinying.lepaiyizu.R;
import com.zhijinying.lepaiyizu.bean.LTDataInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuying on 2016/9/7.
 */
public class LTAdapter extends BaseAdapter{

    private List<LTDataInfo> list;

    public LTAdapter(ArrayList<LTDataInfo> list) {
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

        if(convertView == null){

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lt, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
            holder.tvViews = (TextView) convertView.findViewById(R.id.tvViewsNum);
            holder.tvReply = (TextView) convertView.findViewById(R.id.tvReplyNum);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        LTDataInfo dataInfo = list.get(position);
        holder.tvTitle.setText(dataInfo.getTitle());
        holder.tvAuthor.setText(dataInfo.getAuthor());
        holder.tvViews.setText(dataInfo.getViews());
        holder.tvReply.setText(dataInfo.getReply());

        return convertView;
    }

    class ViewHolder{
        TextView tvTitle, tvAuthor, tvViews, tvReply;
    }
}

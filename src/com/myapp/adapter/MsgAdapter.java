package com.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapp.R;
import com.myapp.objects.Msg;

import java.util.List;

/**
 * Created by megatron on 15/2/19.
 */
public class MsgAdapter extends ArrayAdapter<Msg> {
    private int resource;

    public MsgAdapter(Context context, int resource, List<Msg> msgList) {
        super(context, resource, msgList);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder = null;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resource, null);
            viewHolder = new ViewHolder();
            viewHolder.left = (TextView) view.findViewById(R.id.msg_textView_left);
            viewHolder.right = (TextView) view.findViewById(R.id.msg_textView_right);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(!msg.isSend()){
            viewHolder.left.setVisibility(View.VISIBLE);
            viewHolder.right.setVisibility(View.GONE);
            viewHolder.left.setText(msg.getMsg());
        }
        else{
            viewHolder.right.setVisibility(View.VISIBLE);
            viewHolder.left.setVisibility(View.GONE);
            viewHolder.right.setText(msg.getMsg());
        }
        return view;
    }

    class ViewHolder{
        TextView left;
        TextView right;
    }
}
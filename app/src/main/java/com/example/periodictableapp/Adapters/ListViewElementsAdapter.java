package com.example.periodictableapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.periodictableapp.Activities.ElementDetails;
import com.example.periodictableapp.Element;
import com.example.periodictableapp.R;

import java.util.List;

public class ListViewElementsAdapter extends BaseAdapter {
    private final int layout;
    private final Context context;
    List<Element> elementList;

    public ListViewElementsAdapter(Context context, int layout, List<Element> elements) {
        this.layout = layout;
        this.context = context;
        this.elementList = elements;
    }

    @Override
    public int getCount() {
        return elementList.size();
    }

    @Override
    public Object getItem(int position) {
        return elementList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        LinearLayout elementContainer_elementItem;
        TextView txtelementName_elementItem, txtelementGroupBlock_elementItem, txtElementSymbol_elementItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtElementSymbol_elementItem = row.findViewById(R.id.elementSymbol_elementItem);
            holder.txtelementName_elementItem = row.findViewById(R.id.elementName_elementItem);
            holder.txtelementGroupBlock_elementItem = row.findViewById(R.id.elementGroupBlock_elementItem);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Element element = elementList.get(position);

        holder.elementContainer_elementItem.setOnClickListener(v -> {
            Intent intent = new Intent(context, ElementDetails.class);
            intent.putExtra("Element", (Parcelable) element);
            context.startActivity(intent);
        });

        holder.txtelementName_elementItem.setText(element.getName());
        holder.txtelementGroupBlock_elementItem.setText(element.getGroupBlock());

        return row;
    }
}
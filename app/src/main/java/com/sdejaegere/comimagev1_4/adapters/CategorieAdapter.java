package com.sdejaegere.comimagev1_4.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sdejaegere.comimagev1_4.R;

public class CategorieAdapter extends ArrayAdapter<String> {

    String[] nomCategorie;
    Context mContext;

    public CategorieAdapter (@NonNull Context context, String[] nomCategorie){
        super(context, R.layout.item_categorie_recycler);
        this.nomCategorie = nomCategorie;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return nomCategorie.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CategorieAdapter.ViewHolder mViewHolder = new CategorieAdapter.ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_categorie_recycler, parent, false);
            mViewHolder.nomCategorie = convertView.findViewById(R.id.nomCategorie);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (CategorieAdapter.ViewHolder) convertView.getTag();
        }
        mViewHolder.nomCategorie.setText(nomCategorie[position]);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        TextView nomCategorie;
    }
}

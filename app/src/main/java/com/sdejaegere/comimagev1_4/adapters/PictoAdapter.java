package com.sdejaegere.comimagev1_4.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdejaegere.comimagev1_4.R;

public class PictoAdapter extends ArrayAdapter<String> {

    String[] nomPicto;
    int[] imagePicto;
    Context mContext;

    public PictoAdapter (@NonNull Context context, String[] nom, int[] image){
        super(context, R.layout.item_picto_recycler);
        this.nomPicto = nom;
        this.imagePicto = image;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return nomPicto.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_picto_recycler, parent, false);
            mViewHolder.imagePicto = convertView.findViewById(R.id.imagePicto);
            mViewHolder.nomPicto = convertView.findViewById(R.id.nomPicto);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.imagePicto.setImageResource(imagePicto[position]);
        mViewHolder.nomPicto.setText(nomPicto[position]);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        AppCompatImageView imagePicto;
        TextView nomPicto;
    }
}

package com.example.sqlitenotuygulamasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NotAdapter extends ArrayAdapter<Not>
{
    public NotAdapter(Context context, List<Not> notlar)
    {
        super(context, 0,notlar);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Not not = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.not_blogu, parent, false);

        TextView baslik = convertView.findViewById(R.id.blokBaslik);
        TextView aciklama = convertView.findViewById(R.id.blokAciklama);

        baslik.setText(not.getBaslik());
        aciklama.setText(not.getAciklama());

        return convertView;
    }
}

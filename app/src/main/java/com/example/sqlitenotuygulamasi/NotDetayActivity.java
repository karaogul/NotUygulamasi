package com.example.sqlitenotuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class NotDetayActivity extends AppCompatActivity
{
    private EditText baslikEditText, aciklamaEditText;
    private Button silButton;
    private Not secilenNot;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_detay);
        initWidgets();
        checkForEditNot();

    }

    private void initWidgets() {
        baslikEditText = findViewById(R.id.baslikEditText);
        aciklamaEditText = findViewById(R.id.aciklamaEditText);
        silButton = findViewById(R.id.silNotButton);
    }

    private void checkForEditNot()
    {
        Intent oncekiIntent = getIntent();

        int gecilenNotID = oncekiIntent.getIntExtra(Not.NOT_GUNCELLE_EKSTRA, -1);
        secilenNot = Not.getNotForID(gecilenNotID);

        if (secilenNot != null)
        {
            baslikEditText.setText(secilenNot.getBaslik());
            aciklamaEditText.setText(secilenNot.getAciklama());
        }
        else
        {
            silButton.setVisibility(View.INVISIBLE);
        }
    }

    public void kaydetNot(View view)
    {
        SQLiteManager sqLiteManager = SQLiteManager.databaseOrnegi(this);
        String baslik = String.valueOf(baslikEditText.getText());
        String aciklama = String.valueOf(aciklamaEditText.getText());

        if (secilenNot == null)
        {
            int id = Not.notArrayList.size();
            Not yeniNot = new Not(id, baslik, aciklama);
            Not.notArrayList.add(yeniNot);
            sqLiteManager.ekleNotToDatabase(yeniNot);
        }
        else
        {
            secilenNot.setBaslik(baslik);
            secilenNot.setAciklama(aciklama);
            sqLiteManager.guncelleNotInDB(secilenNot);
        }

        finish();
    }

    public void silNot(View view)
    {
        secilenNot.setSilinen(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.databaseOrnegi(this);
        sqLiteManager.guncelleNotInDB(secilenNot);
        finish();
    }
}




package com.example.sqlitenotuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
    private ListView notListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setNotAdapter();
        setOnClickListener();
    }


    private void initWidgets()
    {
        notListView = findViewById(R.id.notListView);
    }

    private void loadFromDBToMemory()
    {
        SQLiteManager sqLiteManager = SQLiteManager.databaseOrnegi(this);
        sqLiteManager.populateNotListArray();
    }

    private void setNotAdapter()
    {
        NotAdapter notAdapter = new NotAdapter(getApplicationContext(), Not.silinmeyenNotlar());
        notListView.setAdapter(notAdapter);
    }

    private void setOnClickListener()
    {
        notListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Not secilenNot = (Not) notListView.getItemAtPosition(position);
                Intent guncelleNotIntent = new Intent(getApplicationContext(), NotDetayActivity.class);
                guncelleNotIntent.putExtra(Not.NOT_GUNCELLE_EKSTRA, secilenNot.getId());
                startActivity(guncelleNotIntent);
            }
        });
    }


    public void yeniNot(View view)
    {
        Intent yeniNotIntent = new Intent(this, NotDetayActivity.class);
        startActivity(yeniNotIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setNotAdapter();
    }
}
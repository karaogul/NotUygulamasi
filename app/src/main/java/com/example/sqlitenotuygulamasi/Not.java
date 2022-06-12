package com.example.sqlitenotuygulamasi;

import java.util.ArrayList;
import java.util.Date;

public class Not
{
    public static ArrayList<Not> notArrayList = new ArrayList<>();
    public static String NOT_GUNCELLE_EKSTRA = "notGuncelle";

    private int id;
    private String baslik;
    private String aciklama;
    private Date silinen;

    public Not(int id, String baslik, String aciklama, Date deleted)
    {
        this.id = id;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.silinen = deleted;
    }

    public Not(int id, String baslik, String aciklama)
    {
        this.id = id;
        this.baslik = baslik;
        this.aciklama = aciklama;
        silinen = null;
    }

    public static Not getNotForID(int gecilenNotID)
    {
        for (Not not : notArrayList)
        {
            if(not.getId() == gecilenNotID)
                return not;
        }
        return null;
    }

    public static ArrayList<Not> silinmeyenNotlar()
    {
        ArrayList<Not> silinmeyen = new ArrayList<>();
        for(Not not : notArrayList)
        {
            if(not.getSilinen() == null)
                silinmeyen.add(not);
        }

        return silinmeyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Date getSilinen() {
        return silinen;
    }

    public void setSilinen(Date silinen) {
        this.silinen = silinen;
    }
}

package com.example.readnewrss;

import android.net.Uri;

import java.io.Serializable;

public class NewsItem implements Serializable {
    private String tieuDe;
    private String mota;
    private String linkbai;
    private String img;

    public NewsItem(String tieuDe, String mota, String linkbai, String img) {
        this.tieuDe = tieuDe;
        this.mota = mota;
        this.linkbai = linkbai;
        this.img = img;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getLinkbai() {
        return linkbai;
    }

    public void setLinkbai(String linkbai) {
        this.linkbai = linkbai;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

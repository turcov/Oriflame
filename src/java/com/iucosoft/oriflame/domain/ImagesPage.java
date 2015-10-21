/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.domain;

/**
 *
 * @author Turkov S
 */
public class ImagesPage {

    private int id;
    private byte[] imageData;
    private int idPageText;
    private String filename;
    private String directory;

    public ImagesPage() {
    }

    public ImagesPage(byte[] imageData, int idPageText, String filename,String directory) {
        this.imageData = imageData;
        this.idPageText = idPageText;
        this.filename = filename;
        this.directory=directory;
    }

    public ImagesPage(int id, byte[] imageData, int idPageText, String filename,String directory) {
        this.id = id;
        this.imageData = imageData;
        this.idPageText = idPageText;
        this.filename = filename;
        this.directory=directory;
    }

    public int getIdPageText() {
        return idPageText;
    }

    public void setIdPageText(int idPageText) {
        this.idPageText = idPageText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

}

package com.zn.lichen.framework.network;

/**
 * Created by lichen on 2017/1/3.
 */

public class MultiPart {

    private String name;
    private String filename;
    private String filepath;
    private byte[] filedata;
    private String mime = "application/octet-stream";

    public MultiPart(String name, String filename, String filepath) {
        this.name = name;
        this.filename = filename;
        this.filepath = filepath;
        if (filepath.endsWith("jpg") || filepath.endsWith("jpeg")) {
            this.mime = "image/jpg";
        } else if (filepath.endsWith("png")) {
            this.mime = "image/png";
        }
    }

    public MultiPart(String name, String filename, byte[] filedata) {
        this.name = name;
        this.filename = filename;
        this.filedata = filedata;
    }

    public MultiPart(String name, String filename, String filepath, String mime) {
        this.name = name;
        this.filename = filename;
        this.filepath = filepath;
        this.mime = mime;
    }

    public MultiPart(String name, String filename, byte[] filedata, String mime) {
        this.name = name;
        this.filename = filename;
        this.filedata = filedata;
        this.mime = mime;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public String getMime() {
        return mime;
    }

}

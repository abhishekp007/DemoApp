package com.example.demoapp.model;

public class DataModel {

    private int DataId;
    private String name;
    private String desc;
    private String img_path;

    public DataModel(int dataId, String name, String desc) {
        DataId = dataId;
        this.name = name;
        this.desc = desc;
//        this.img_path = img_path;
    }

    public int getDataId() {
        return DataId;
    }

    public void setDataId(int dataId) {
        DataId = dataId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

//    public String getImg_path() {
//        return img_path;
//    }
//
//    public void setImg_path(String img_path) {
//        this.img_path = img_path;
//    }
}

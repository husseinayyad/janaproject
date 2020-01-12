package com.fuais.janaproject;

public class course {
    String android ,java,database,datastr,oop;

    public course() {
    }

    public course(String android, String java, String database, String datastr, String oop) {
        this.android = android;
        this.java = java;
        this.database = database;
        this.datastr = datastr;
        this.oop = oop;
    }

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getJava() {
        return java;
    }

    public void setJava(String java) {
        this.java = java;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatastr() {
        return datastr;
    }

    public void setDatastr(String datastr) {
        this.datastr = datastr;
    }

    public String getOop() {
        return oop;
    }

    public void setOop(String oop) {
        this.oop = oop;
    }
}

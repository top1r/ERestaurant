package com.bionic.erestaurant.core;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimeDateAdapter extends XmlAdapter<String, Timestamp>{

    @Override
    public String marshal(Timestamp v) throws Exception {
        return v.toString();
    }

    @Override
    public Timestamp unmarshal(String v) throws Exception {
        return new Timestamp(Long.parseLong(v));
    }

}

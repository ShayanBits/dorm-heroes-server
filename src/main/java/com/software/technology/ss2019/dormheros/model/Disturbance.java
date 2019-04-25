package com.software.technology.ss2019.dormheros.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Disturbance {
    @Id
    public ObjectId _id;

    public String type;

    public Disturbance(ObjectId _id, String type) {
        this._id = _id;
        this.type = type;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

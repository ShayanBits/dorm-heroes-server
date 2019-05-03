package com.software.technology.ss2019.dormheroes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class DisturbanceType {
    @Id
    public ObjectId _id;

    public String type;

    public DisturbanceType(ObjectId _id, String type) {
        this._id = _id;
        this.type = type;
    }

    public String get_id() {
        return this._id.toHexString();
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

    @Override
    public String toString() {
        return "DisturbanceType{" +
                "_id=" + _id +
                ", type='" + type + '\'' +
                '}';
    }
}

package com.example.motorcycleordermanagement.model.database.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "motorcycles")
public class Motorcycle implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "motorcycle_id")
    private int motorcycleId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "capacity")
    private int capacity;
    @ColumnInfo(name = "count")
    private int count = 1;
    @ColumnInfo(name = "image")
    private String image = "";

    public int getMotorcycleId() {
        return motorcycleId;
    }

    public void setMotorcycleId(int motorcycleId) {
        this.motorcycleId = motorcycleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

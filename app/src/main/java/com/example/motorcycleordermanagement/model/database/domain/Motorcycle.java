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
    private long motorcycleId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "capacity")
    private int capacity;
    @ColumnInfo(name = "count")
    private int count;

    public long getMotorcycleId() {
        return motorcycleId;
    }

    public void setMotorcycleId(long motorcycleId) {
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
}

package com.example.motorcycleordermanagement.model.database.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(
        tableName = "detail_order",
        primaryKeys = {"motorcycle_id", "order_id"},
        foreignKeys = {
                @ForeignKey(
                        entity = Motorcycle.class,
                        parentColumns = "motorcycle_id",
                        childColumns = "motorcycle_id",
                        onDelete = CASCADE,
                        onUpdate = CASCADE
                ),

                @ForeignKey(
                        entity = Order.class,
                        parentColumns = "order_id",
                        childColumns = "order_id",
                        onDelete = CASCADE,
                        onUpdate = CASCADE
                )
        }
)
public class DetailOrder implements Serializable {
    @ColumnInfo(name = "motorcycle_id")
    private int motorcycleId;
    @ColumnInfo(name = "order_id")
    private int orderId;
    @ColumnInfo(name = "count")
    private int count;

    public int getMotorcycleId() {
        return motorcycleId;
    }

    public void setMotorcycleId(int motorcycleId) {
        this.motorcycleId = motorcycleId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

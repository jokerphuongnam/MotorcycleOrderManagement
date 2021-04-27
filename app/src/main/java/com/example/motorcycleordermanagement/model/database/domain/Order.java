package com.example.motorcycleordermanagement.model.database.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "orders")
public class Order implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_id")
    private int orderId;
    @ColumnInfo(name = "customer")
    private String customer;
    @ColumnInfo(name = "date_order")
    private long dateOrder;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public long getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(long dateOrder) {
        this.dateOrder = dateOrder;
    }

    @NonNull
    private Calendar calendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        if (dateOrder != 0) {
            calendar.setTimeInMillis(dateOrder);
        }
        return calendar;
    }

    public String getDateCreateString() {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar().getTime());
    }
}

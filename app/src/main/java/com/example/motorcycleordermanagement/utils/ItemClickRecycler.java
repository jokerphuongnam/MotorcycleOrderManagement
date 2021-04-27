package com.example.motorcycleordermanagement.utils;

public interface ItemClickRecycler<T> {
    void delete(T item);
    void edit(T item);
}

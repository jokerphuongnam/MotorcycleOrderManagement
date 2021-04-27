package com.example.motorcycleordermanagement.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

public class ObjectUtil {
    @NotNull
    public static <T> T clone(T object, @NotNull Gson gson) {
        String json = gson.toJson(object);
        return gson.fromJson(json, new TypeToken<T>() {
        }.getType());
    }
}

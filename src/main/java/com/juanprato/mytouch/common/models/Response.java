package com.juanprato.mytouch.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("errors")
    private final List<String> errors = new ArrayList<String>();

    public Response(Exception ex) {
        this.data = null;
        this.errors.add(ex.getMessage());
    }

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

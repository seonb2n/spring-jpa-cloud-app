package com.example.demo.support;

import java.util.List;

//Json 데이터 List 를 감싸주는 Wrapper
public class Wrapper <T>{
    private List<T> data;

    public Wrapper() {}

    public Wrapper(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

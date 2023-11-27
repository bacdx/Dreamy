package com.example.dreamy.Model;

import java.io.Serializable;

public class PhotoSlide implements Serializable {
    private int resourceId;

    public PhotoSlide(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}

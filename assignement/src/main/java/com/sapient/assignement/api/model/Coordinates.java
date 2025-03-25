package com.sapient.assignement.api.model;

import javax.persistence.Embeddable;

@Embeddable
public class Coordinates {
    private Double lat;
    private Double lng;




    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}

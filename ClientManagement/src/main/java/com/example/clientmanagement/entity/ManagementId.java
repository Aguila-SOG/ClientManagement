package com.example.clientmanagement.entity;

import java.io.Serializable;
import java.util.Objects;

public class ManagementId implements Serializable {

    private Integer facYear;
    private Integer quarterly;

    public ManagementId() {
    }

    public ManagementId(Integer facYear, Integer quarterly) {
        this.facYear = facYear;
        this.quarterly = quarterly;
    }

    public Integer getFacYear() {
        return facYear;
    }

    public void setFacYear(Integer facYear) {
        this.facYear = facYear;
    }

    public Integer getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(Integer quarterly) {
        this.quarterly = quarterly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagementId that = (ManagementId) o;
        return Objects.equals(facYear, that.facYear) && Objects.equals(quarterly, that.quarterly);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facYear, quarterly);
    }
}
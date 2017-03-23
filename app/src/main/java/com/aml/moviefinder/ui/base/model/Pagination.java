package com.aml.moviefinder.ui.base.model;

import java.net.Inet4Address;

/**
 * Created by owner on 4/11/16.
 */


public class Pagination {

    public static final Integer DEFAULT_PAGE_SIZE=100;
    public static final Integer DEFAULT_START_PAGE=1;

    private Boolean last;
    private Long totalElements;
    private Long totalPages;
    private Boolean first;
    private Long numberOfElements;
    private Integer size=DEFAULT_PAGE_SIZE;
    private Integer number=DEFAULT_START_PAGE;

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

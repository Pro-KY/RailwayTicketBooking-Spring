package com.proky.booking.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
public class PageDto implements Serializable {
    private  final int DEFAULT_START_INDEX = 0;
    private final int DEFAULT_PAGE_SIZE = 3;

    private Integer currentPageIndex;
    private Integer pageSize;

    private Boolean isNextClicked = false;
    private Boolean isPreviousClicked = false;
    private Boolean isLeftButtonDisabled = false;
    private Boolean isRightButtonDisabled = false;
    private Long startPageIndex;
    private Long endPageIndex;
    private long allPagesAmount;

    private Long userId;
    private List pageList;

    public PageDto() {
        this.currentPageIndex = DEFAULT_START_INDEX;
        pageSize = DEFAULT_PAGE_SIZE;
    }

    public PageDto(Integer currentPageIndex, Integer pageSize, Boolean isNextClicked, Boolean isPreviousClicked) {

        if (pageSize != null && !pageSize.equals(this.pageSize)) {
            this.pageSize = pageSize;
            this.currentPageIndex = DEFAULT_START_INDEX;
        } else {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }

        this.currentPageIndex = (currentPageIndex != null) ? currentPageIndex : DEFAULT_START_INDEX;
        this.isNextClicked = (isNextClicked != null) ? isNextClicked : false;
        this.isPreviousClicked = (isPreviousClicked != null) ? isPreviousClicked : false;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "currentPageIndex=" + currentPageIndex +
                ", pageSize=" + pageSize +
                ", isNextClicked=" + isNextClicked +
                ", isPreviousClicked=" + isPreviousClicked +
                ", isLeftButtonDisabled=" + isLeftButtonDisabled +
                ", isRightButtonDisabled=" + isRightButtonDisabled +
                ", startPageIndex=" + startPageIndex +
                ", endPageIndex=" + endPageIndex +
                ", allPagesAmount=" + allPagesAmount +
                ", pageList=" + pageList +
                '}';
    }
}

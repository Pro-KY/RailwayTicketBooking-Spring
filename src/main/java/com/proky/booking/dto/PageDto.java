package com.proky.booking.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto implements Serializable {
    private  final int DEFAULT_START_INDEX = 0;
    private final int DEFAULT_PAGE_SIZE = 3;

    private Integer currentPageIndex = DEFAULT_START_INDEX;
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    private Boolean isNextClicked = false;
    private Boolean isPreviousClicked = false;
    private Boolean isLeftButtonDisabled = false;
    private Boolean isRightButtonDisabled = false;
    private Long startPageIndex;
    private Long endPageIndex;
    private long allPagesAmount;

    private Long userId;
    private List pageList;

    public void updatePageDtoValues(Integer pageSize, Boolean prevPageClick, Boolean nextPageClick, Integer selectedPageIndex) {
        currentPageIndex = (selectedPageIndex != null) ? selectedPageIndex : currentPageIndex;
        isNextClicked = (nextPageClick != null) ? nextPageClick : false;
        isPreviousClicked = (prevPageClick != null) ? prevPageClick : false;
//        this.pageSize = (pageSize != null) ? pageSize : DEFAULT_PAGE_SIZE;

        if (pageSize != null && !pageSize.equals(this.pageSize)) {
            this.pageSize = pageSize;
            currentPageIndex = DEFAULT_START_INDEX;
        } else {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
    }
}

package com.proky.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto implements Serializable {
    private static final long DEFAULT_START_INDEX = 0;

    private Long currentPageIndex = DEFAULT_START_INDEX;
    private Boolean isNextClicked;
    private Boolean isPreviousClicked;
    private String pageSize;
    private boolean isLeftButtonDisabled;
    private boolean isRightButtonDisabled;
    private Long startPageIndex;
    private Long endPageIndex;
    private long allPagesAmount;

    private Long userId;
    private List pageList;
}

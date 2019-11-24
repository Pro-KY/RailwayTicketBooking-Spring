package com.proky.booking.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto implements Serializable {
    private Integer currentPageIndex = 0;
    private Integer pageSize = 3;

    private Boolean isNextClicked = false;
    private Boolean isPreviousClicked = false;
    private Boolean isLeftButtonDisabled = false;
    private Boolean isRightButtonDisabled = false;
    private Long startPageIndex;
    private Long endPageIndex;
    private long allPagesAmount;

    private Long userId;
    private List pageList;
}

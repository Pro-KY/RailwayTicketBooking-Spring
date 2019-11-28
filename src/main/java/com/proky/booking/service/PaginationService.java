package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaginationService {
    private int pageSize;
    private long startVisibleIndex;
    private long endVisibleIndex;
    private int currentPageIndex;
    private int allPagesAmount;

    private static final int DEFAULT_PAGE_SIZE = 3; // 5
    private static final int DEFAULT_START_INDEX = 0;
    private static final int DEFAULT_END_INDEX = 4;

    private boolean isLeftButtonDisabled;
    private boolean isRightButtonDisabled;
    private PageDto pageDto;

    public PageDto getpageDto() {
        return pageDto;
    }

    public void setPageDto(final PageDto pageDto) {
        this.pageDto = pageDto;

        final Long startVisibleIndex = this.pageDto.getStartPageIndex();
        final Long endVisibleIndex = this.pageDto.getEndPageIndex();
        final Integer currentPageIndex = this.pageDto.getCurrentPageIndex();

        this.startVisibleIndex = (startVisibleIndex != null) ? startVisibleIndex : DEFAULT_START_INDEX;
        this.endVisibleIndex = (endVisibleIndex != null) ? endVisibleIndex : DEFAULT_END_INDEX;
        this.currentPageIndex = (currentPageIndex != null) ? currentPageIndex : DEFAULT_START_INDEX;
        final Integer pageSize = this.pageDto.getPageSize();
        this.pageSize = (pageSize != null) ? this.pageDto.getPageSize() : DEFAULT_PAGE_SIZE;
    }

    private static final Logger log = LogManager.getLogger(PaginationService.class);

    public PaginationService() { }

    public PaginationService(PageDto pageDto) {
        this.pageDto = pageDto;
    }


    public void calculatePageIndex() {
//        final Long startVisibleIndex = pageDto.getStartPageIndex();
//        final Long endVisibleIndex = pageDto.getEndPageIndex();
//        final Integer currentPageIndex = pageDto.getCurrentPageIndex();
//
//        this.startVisibleIndex = (startVisibleIndex != null) ? startVisibleIndex : DEFAULT_START_INDEX;
//        this.endVisibleIndex = (endVisibleIndex != null) ? endVisibleIndex : DEFAULT_END_INDEX;
//        this.currentPageIndex = (currentPageIndex != null) ? currentPageIndex : DEFAULT_START_INDEX;
//        final Integer pageSize = pageDto.getPageSize();
//        this.pageSize = (pageSize != null) ? pageDto.getPageSize() : DEFAULT_PAGE_SIZE;


//        calculateEndVisibleIndex();
//        changeButtonsState();

        if (pageDto.getIsNextClicked()) {
            handleNextButtonClick();
        } else if (pageDto.getIsPreviousClicked()) {
            handlePreviousButtonClick();
        }
    }

    public void changeIndexBoundariesAndButtonsState() {
        calculateEndVisibleIndex();
        changeButtonsState();
    }

    public void updatePageDto() {
        pageDto.setCurrentPageIndex(currentPageIndex);
        pageDto.setStartPageIndex(startVisibleIndex);
        pageDto.setEndPageIndex(endVisibleIndex);
        pageDto.setIsLeftButtonDisabled(isLeftButtonDisabled);
        pageDto.setIsRightButtonDisabled(isRightButtonDisabled);
        pageDto.setAllPagesAmount(allPagesAmount);
        pageDto.setPageSize(pageSize);
    }

    private void calculateEndVisibleIndex() {
        log.debug("endVisibleIndex before: {}", endVisibleIndex);
        if (allPagesAmount == 0) {
            endVisibleIndex = 0;
        } else if (endVisibleIndex == 0 & allPagesAmount > DEFAULT_END_INDEX) {
            endVisibleIndex = DEFAULT_END_INDEX;
        } else if (allPagesAmount < DEFAULT_END_INDEX) {
            endVisibleIndex = allPagesAmount - 1;
        }
        log.debug("endVisibleIndex after: {}", endVisibleIndex);
    }

    private void handleNextButtonClick() {
        if (currentPageIndex < endVisibleIndex) {
            currentPageIndex += 1;
        } else if (currentPageIndex == endVisibleIndex) {
            shiftPagesIndexes(true);
        }
        changeButtonsState();
    }

    private void handlePreviousButtonClick() {
        if (currentPageIndex > startVisibleIndex) {
            currentPageIndex -= 1;
        } else if (currentPageIndex == startVisibleIndex) {
            shiftPagesIndexes(false);
        }
        changeButtonsState();
    }

    public void changeButtonsState() {
        isLeftButtonDisabled = (currentPageIndex == startVisibleIndex) & (currentPageIndex == DEFAULT_START_INDEX);
        isRightButtonDisabled = currentPageIndex == allPagesAmount - 1;
    }

    private void shiftPagesIndexes(boolean forward) {
        startVisibleIndex += forward ? 1 : -1;
        endVisibleIndex += forward ? 1 : -1;
        currentPageIndex += forward ? 1 : -1;

        log.debug("shift indexes");
        log.debug("startVisibleIndex: {}", startVisibleIndex);
        log.debug("endVisibleIndex: {}", endVisibleIndex);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setAllPagesAmount(int allPagesAmount) {
        this.allPagesAmount = allPagesAmount;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }


}

package com.demo.todoservice.util;

import com.demo.todoservice.constants.CommonConstants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageUtil {

    private PageUtil() {
    }

    public static PageRequest buildPageRequest(Integer pageNumber, Integer pageSize, String sortBy) {
        int queryPageNumber;
        int queryPageSize;
        Sort pageSort;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = CommonConstants.DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = CommonConstants.DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > CommonConstants.PAGE_SIZE_LIMIT) {
                queryPageSize = CommonConstants.PAGE_SIZE_LIMIT;
            } else {
                queryPageSize = pageSize;
            }
        }

        if (sortBy != null) {
            pageSort = Sort.by(Sort.Order.asc(sortBy));

            return PageRequest.of(queryPageNumber, queryPageSize, pageSort);
        }

        return PageRequest.of(queryPageNumber, queryPageSize);
    }
}

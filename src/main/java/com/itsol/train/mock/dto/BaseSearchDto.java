package com.itsol.train.mock.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

@Getter
@Setter
public class BaseSearchDto {
    private List<?> data;
    private long totalRecords;
    private int page;
    List<SortDto> sorts;
    private String sort;
    private String orderBy;
    private int pageSize;
}

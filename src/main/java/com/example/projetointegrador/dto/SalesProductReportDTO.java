package com.example.projetointegrador.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SalesProductReportDTO {
    private String salesProductReport;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String user;
    private Integer totalProduct;
    private List<SalesProductReportListDTO> products;

}

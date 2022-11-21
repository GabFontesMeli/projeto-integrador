package com.example.projetointegrador.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportBatchProductDTO {
    private List<ReportProductDTO> products;
}

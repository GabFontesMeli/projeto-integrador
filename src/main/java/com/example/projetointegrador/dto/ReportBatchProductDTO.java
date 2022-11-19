package com.example.projetointegrador.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportBatchProductDTO {
    private List<ReportProductDTO> products;
}

package com.example.projetointegrador.dto;

import com.example.projetointegrador.enums.CartStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private LocalDate date;
    private Long userId;
    private CartStatusEnum status;
    private List<CartItemDTO> products;
}

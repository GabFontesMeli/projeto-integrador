package com.example.projetointegrador.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.projetointegrador.model.CartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    
    private LocalDate date;
    private Long userId;
    private Boolean status;
    private List<CartItem> products;
}

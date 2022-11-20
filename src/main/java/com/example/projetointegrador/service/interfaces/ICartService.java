package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.dto.CompletedSaleReportCartDTO;
import com.example.projetointegrador.dto.SaleInfoCartDTO;
import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.exceptions.InsufficientStockException;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.exceptions.UserUNotFoundException;
import com.example.projetointegrador.model.Cart;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ICartService {
    Double createCart(CartDTO cartDTO) throws UserUNotFoundException, InsufficientStockException, ProductNotFoundException, ExpiredProductException;
    String changeCartStatus(Long cartId, CartStatusDTO cartStatusDTO);

    CompletedSaleReportCartDTO salesReportByPeriod(String startDate, String endDate);
}

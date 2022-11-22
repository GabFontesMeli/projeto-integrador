package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.dto.CompletedFinanceReportCartDTO;
import com.example.projetointegrador.exceptions.*;
import com.example.projetointegrador.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * Create a new cart based in the CartDTO parameter.
     * @param cartDTO Contains the date, userId, status and the products to be added to the cart.
     * @return Return the total value of the purchase.
     * @throws UserUNotFoundException
     * @throws InsufficientStockException
     * @throws ProductNotFoundException
     * @throws ExpiredProductException
     */
    @PostMapping
    public ResponseEntity<Double> createCart(@RequestBody CartDTO cartDTO) throws UserUNotFoundException, InsufficientStockException, ProductNotFoundException, ExpiredProductException {
         return new ResponseEntity<>(cartService.createCart(cartDTO), HttpStatus.CREATED);
    }

    /**
     * Update the status of a cart by id.
     * @param cartId Id of the cart to be updated.
     * @param cartStatusDTO Contains the new status of the cart.
     * @return The cart with status updated.
     */
    @PutMapping("/{cartId}")
    public ResponseEntity<String> changeCartStatus(@PathVariable Long cartId, @RequestBody CartStatusDTO cartStatusDTO){
        return new ResponseEntity<>(cartService.changeCartStatus(cartId, cartStatusDTO), HttpStatus.OK);
    }

    /**
     * This routes returns a finance report of the sales made in a specified period.
     * @param startDate The initial period filter.
     * @param endDate The limit period filter.
     * @return CompletedFinanceReportCartDTO with the total value sum of all sales and a list of all sales made in the period.
     * @throws CartNotFoundException
     * @throws InvalidDateFormatException
     */
    @GetMapping("/finance-report-by-period/{startDate}/{endDate}")
    public ResponseEntity<CompletedFinanceReportCartDTO> financeReportByPeriod(@PathVariable String startDate, @PathVariable String endDate) throws CartNotFoundException {
        return new ResponseEntity<>(cartService.financeReportByPeriod(startDate, endDate), HttpStatus.OK);
    }

    /**
     * Cancels the order by id and changes the cart status to "CANCELED".
     * @param cartId Id of the cart to be canceled.
     * @param userId Id of the user that wants to cancel the order.
     * @return CartStatusDTO object with updated status.
     * @throws InvalidUserException
     * @throws CartNotFoundException
     * @throws UnfinishedOrderException
     * @throws ExpiredCancellationPeriodException
     */
    @PutMapping("/{cartId}/{userId}")
    public ResponseEntity<CartStatusDTO> cancelOrder(@PathVariable Long cartId, @PathVariable Long userId) throws InvalidUserException, CartNotFoundException, UnfinishedOrderException, ExpiredCancellationPeriodException {
        return new ResponseEntity<>(cartService.cancelOrder(cartId, userId), HttpStatus.OK);
    }
}

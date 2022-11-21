package com.example.projetointegrador.service.report;

import com.example.projetointegrador.dto.report.SalesProductReportDTO;
import com.example.projetointegrador.dto.report.SalesProductReportListDTO;
import com.example.projetointegrador.exceptions.PeriodInvalidException;
import com.example.projetointegrador.repository.CartRepository;
import com.example.projetointegrador.service.interfaces.ISalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesProductReportService implements ISalesReportService {

    @Autowired
    private CartRepository cartRepository;

    /**Method to sold consult products sold by period
     * @param start
     * @param end
     * @return
     * @throws PeriodInvalidException
     */
    @Override
    public SalesProductReportDTO getSalesProductReportByPeriod(LocalDate start, LocalDate end) throws PeriodInvalidException {
        checkValidPeriod(start, end);
        List<Object[]> products = cartRepository.getSalesProductReportByDate(start, end);
        return buildSalesProductReport(products, start, end);
    }

    /**Method to sold consult products sold by period and userId
     * @param start
     * @param end
     * @param idUser
     * @return
     * @throws PeriodInvalidException
     */
    @Override
    public SalesProductReportDTO getSalesProductReportByUserPeriod(LocalDate start, LocalDate end, Long idUser) throws PeriodInvalidException {
        checkValidPeriod(start, end);
        List<Object[]> products = cartRepository.getSalesProductReportByUserPeriod(start, end, idUser);

        return buildSalesProductReport(products, start, end);
    }

    @Override
    public SalesProductReportDTO getSalesProductReportByUsersPeriod(LocalDate start, LocalDate end) {
        return null;
    }

    public SalesProductReportDTO buildSalesProductReport(List<Object[]> products, LocalDate start, LocalDate end) {
        SalesProductReportDTO salesProductReportDTO = null;
        if(products.isEmpty()){
             salesProductReportDTO = SalesProductReportDTO.builder()
                    .salesProductReport("Sales products report between " + start + " and " + end + " doesn't have any data")
                     .totalProduct(0)
                    .build();
        } else{
            List<SalesProductReportListDTO> report = products.stream().map(SalesProductReportListDTO::new).collect(Collectors.toList());
             salesProductReportDTO = SalesProductReportDTO.builder()
                    .salesProductReport("Sales products report between " + start + " and " + end)
                     .user(products.get(0).length == 3 ? (String)products.get(0)[2] : null)
                    .totalProduct(report.stream().map(r -> r.getQuantity()).reduce(0, Integer::sum))
                    .products(report)
                    .build();
        }
        return salesProductReportDTO;
    }


    public void checkValidPeriod(LocalDate start, LocalDate end) throws PeriodInvalidException {
        if(start.isAfter(end)){
            throw new PeriodInvalidException("star date cannot be greater than end date");
        }
    }

}

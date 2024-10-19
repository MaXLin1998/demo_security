package com.example.practice.web.order;

import lombok.Data;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class OrderForm {
        //注文番号
        private int orderId;
        //納入日付
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate orderDate;
        //会社番号
        private int companyNo;
        //会社名
        @Size(max=255)
        private String companyName;
        //品物番号
        private int itemNo;
        //品物
        @Size(max=255)
        private String item;
        //数量
        private int quantity;
        //単価
        private int unitPrice;
        //金額
        private int price;
}

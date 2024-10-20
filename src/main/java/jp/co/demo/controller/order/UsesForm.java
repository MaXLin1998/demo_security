package jp.co.demo.controller.order;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class UsesForm {
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

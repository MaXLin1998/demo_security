package jp.co.demo.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class UserList {
    /*
    フィールド(メンバ変数)
    */
    //注文番号
    private int orderId;
    //納入日付
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    //会社番号
    private int companyNo;
    //会社名
    private String companyName;
    //品物番号
    private int itemNo;
    //品物
    private String item;
    //数量
    private int quantity;
    //単価
    private int unitPrice;
    //金額
    private int price;

}
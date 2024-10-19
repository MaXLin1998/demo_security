package com.example.practice.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.practice.web.order.OrderForm;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.transaction.annotation.Transactional;
import java.lang.IllegalAccessException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    @Transactional(readOnly = true)
    public List<OrderEntity> findAll() {
        return repository.findAll();
    }
    @Transactional
    public void create(OrderForm form, Model model) {
        int nextOrderId = getNextOrderId(); // 事前にorderIdを取得するメソッド
        form.setOrderId(nextOrderId); // OrderFormに次のorderIdを設定
        repository.insert(form); // OrderFormをDBに挿入
    }
    // 最大orderId + 1を計算して返すメソッド
    public int getNextOrderId() {
        Integer maxOrderId = repository.findMaxOrderId();
        if (maxOrderId == null) {
            return 1; // 最初の場合、1を返す
        }
        int nextId = maxOrderId + 1;
        System.out.println(nextId);
        return nextId;
    }
    //指定された orderId を使用して、データベースから該当する注文情報を取得するメソッド
    public OrderEntity findById(long orderId) {
        return repository.findById(orderId);
    }
    //渡された OrderForm オブジェクトの情報を使用して、データベース内の注文情報を更新するためメソッド
    @Transactional
    public void update(OrderForm form, Model model) {
        System.out.println("Updating order with orderId: " + form.getOrderId()); // デバッグログを追加
        repository.update(form);
        System.out.println("Order updated successfully."); // デバッグログを追加
    }
    //指定された orderId を使用して、データベース内の注文情報を削除するためメソッド
    @Transactional
    public void delete(long orderId) {
        repository.delete(orderId);
    }
}

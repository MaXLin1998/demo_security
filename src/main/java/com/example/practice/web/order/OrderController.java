package com.example.practice.web.order;

import com.example.practice.domain.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping
    public String showList(Model model) {
        //orderServiceクラスのfindAllメソッドで取得されたオーダーリストを、orderListでビューに渡す
        model.addAttribute("orderList", orderService.findAll());
        // "order/list"という名前のThymeleafビューを表示する
        return "order/list";
    }
    @GetMapping("/form")
    public String showForm(@ModelAttribute OrderForm form, Model model) {
        int nextOrderId = orderService.getNextOrderId();
        form.setOrderId(nextOrderId);
        form.setOrderDate(LocalDate.now());
        model.addAttribute("nextOrderId", nextOrderId);
        return "order/form";
    }
    @PostMapping
    public String create(@Validated OrderForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(form, model);
        }
        orderService.create(form, model);
        return "redirect:/orders";
    }
    @GetMapping("/{orderId}")
    public String showDetail(@PathVariable("orderId") long orderId, Model model) {
        // 特定の注文の詳細を表示するためのメソッド
        // URLパラメータからorderIdを受け取り、その注文の詳細情報をデータベースから取得してモデルに追加
        model.addAttribute("order", orderService.findById(orderId));
        return "order/detail"; // 注文の詳細を表示するためのビューにリダイレクト
    }

    @PostMapping("/update")
    public String update(@Validated OrderForm form, BindingResult bindingResult, Model model) {
        // 注文情報を更新するためのメソッド
        // フォームデータを受け取り、バリデーションエラーがある場合はフォームを再表示
        if (bindingResult.hasErrors()) {
            return showForm(form, model); // 更新フォームを表示するためのメソッドを呼び出し
        }
        orderService.update(form, model); // 注文情報を更新するメソッドを呼び出す
        System.out.println("OrderForm:" + form); // フォームデータをコンソールに出力（デバッグ用）
        return "redirect:/orders"; // オーダー一覧画面にリダイレクト
    }

    @GetMapping("/delete/{orderId}")
    public String showDeleteConfirmation(@PathVariable("orderId") long orderId, Model model) {
        // 注文の削除確認画面を表示するためのメソッド
        // URLパラメータからorderIdを受け取り、削除対象の注文の情報をデータベースから取得してモデルに追加
        model.addAttribute("order", orderService.findById(orderId));
        return "order/delete_confirmation"; // 削除確認画面を表示するためのビューにリダイレクト
    }

    @PostMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") long orderId) {
        // 注文を削除するためのメソッド
        orderService.delete(orderId); // 指定されたorderIdに対応する注文をデータベースから削除
        return "redirect:/orders"; // オーダー一覧画面にリダイレクト
    }

}
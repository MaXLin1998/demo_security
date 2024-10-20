package jp.co.demo.controller.order;

import jp.co.demo.service.GetUsersService;
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
public class GetUsersController {
    private final GetUsersService usersService;
    @GetMapping
    public String showList(Model model) {
        //orderServiceクラスのfindAllメソッドで取得されたオーダーリストを、orderListでビューに渡す
        model.addAttribute("orderList", usersService.findAll());
        // "service/list"という名前のThymeleafビューを表示する
        return "order/list";
    }
    @GetMapping("/form")
    public String showForm(@ModelAttribute UsesForm form, Model model) {
        int nextOrderId = usersService.getNextOrderId();
        form.setOrderId(nextOrderId);
        form.setOrderDate(LocalDate.now());
        model.addAttribute("nextOrderId", nextOrderId);
        return "order/form";
    }
    @PostMapping
    public String create(@Validated UsesForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(form, model);
        }
        usersService.create(form, model);
        return "redirect:/orders";
    }
    @GetMapping("/{orderId}")
    public String showDetail(@PathVariable("orderId") long orderId, Model model) {
        // 特定の注文の詳細を表示するためのメソッド
        // URLパラメータからorderIdを受け取り、その注文の詳細情報をデータベースから取得してモデルに追加
        model.addAttribute("order", usersService.findById(orderId));
        return "order/detail"; // 注文の詳細を表示するためのビューにリダイレクト
    }

    @PostMapping("/update")
    public String update(@Validated UsesForm form, BindingResult bindingResult, Model model) {
        // 注文情報を更新するためのメソッド
        // フォームデータを受け取り、バリデーションエラーがある場合はフォームを再表示
        if (bindingResult.hasErrors()) {
            return showForm(form, model); // 更新フォームを表示するためのメソッドを呼び出し
        }
        usersService.update(form, model); // 注文情報を更新するメソッドを呼び出す
        System.out.println("UsesForm:" + form); // フォームデータをコンソールに出力（デバッグ用）
        return "redirect:/orders"; // オーダー一覧画面にリダイレクト
    }

    @GetMapping("/delete/{orderId}")
    public String showDeleteConfirmation(@PathVariable("orderId") long orderId, Model model) {
        // 注文の削除確認画面を表示するためのメソッド
        // URLパラメータからorderIdを受け取り、削除対象の注文の情報をデータベースから取得してモデルに追加
        model.addAttribute("order", usersService.findById(orderId));
        return "order/delete_confirmation"; // 削除確認画面を表示するためのビューにリダイレクト
    }

    @PostMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") long orderId) {
        // 注文を削除するためのメソッド
        usersService.delete(orderId); // 指定されたorderIdに対応する注文をデータベースから削除
        return "redirect:/orders"; // オーダー一覧画面にリダイレクト
    }

}
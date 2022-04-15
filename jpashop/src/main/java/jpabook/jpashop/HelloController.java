package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    // Model - 스프링의 UI, 어떤 데이터를 실어서 뷰에 넘길 수 있음
    public String hello(Model model) {
        // 이름이 data라는 키의 값을 hello!!로 넘기겠다는 것
        model.addAttribute("data", "hello!!");
        // 화면 이름
        // resources - templates
        return "hello";
    }
}

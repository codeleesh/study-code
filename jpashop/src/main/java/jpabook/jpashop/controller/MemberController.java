package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("members/new")
    public String creatForm(final Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(@Valid final MemberForm memberForm, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }
        final Address address = Address.createAddress(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        final Member member = Member.of(memberForm.getName(), address);
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("members")
    public String list(final Model model) {
        final List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/list";
    }
}

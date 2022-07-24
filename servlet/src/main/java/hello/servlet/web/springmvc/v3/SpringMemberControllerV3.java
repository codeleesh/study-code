package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    @GetMapping
    public String members(final Model model) {
        final List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") final String username,
                             @RequestParam("age") final int age,
                             final Model model) {

        final Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}

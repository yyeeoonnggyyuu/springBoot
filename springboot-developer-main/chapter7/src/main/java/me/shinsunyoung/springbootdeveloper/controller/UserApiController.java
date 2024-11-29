package me.shinsunyoung.springbootdeveloper.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.dto.AddUserRequest;
import me.shinsunyoung.springbootdeveloper.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private  final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request);
        return "redirect:/login";
    }

//    @GetMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password, Model model) {
//        log.info("[BlogViewController] login");
//
//
//        // 서비스에서 이메일과 비밀번호로 사용자 찾기
//        Optional<User> userOptional = userService.findUserByEmailAndPassword(email, password);
//
//        // 사용자가 있으면 로그인 성공 페이지로, 없으면 회원가입 페이지로 리다이렉트
//        if (userOptional.isPresent()) {
//            model.addAttribute("user", userOptional.get());
//            return "articleList";  // 로그인 성공 페이지
//        } else {
//            model.addAttribute("errorMessage", "회원 정보가 없습니다. 회원가입을 해주세요.");
//            return "redirect:/signup";  // 회원가입 페이지로 리다이렉트
//        }
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}

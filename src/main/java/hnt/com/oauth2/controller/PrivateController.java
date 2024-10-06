package hnt.com.oauth2.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateController {

    @GetMapping("/messages")
    public String messages(@AuthenticationPrincipal OAuth2User user, Model model) {
        model.addAttribute("body", user.getAttribute("name"));
        return "response";
    }
}

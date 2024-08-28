package com.example.Hotel.web.auth;

import com.example.Hotel.domain.auth.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/request-password-reset")
    public String requestPasswordPage(Model model) {
        return "/passReset/mailInputForm"; // HTMLページの名前
    }

    @PostMapping("/request-password-reset")
    public String requestPasswordReset(@RequestParam String email, RedirectAttributes redirectAttributes) {
        passwordResetService.sendPasswordResetEmail(email);

        String successMessage = messageSource.getMessage("mailSend.success", null, null);
        // 成功メッセージをFlashAttributesに追加
        redirectAttributes.addFlashAttribute("successMessage", successMessage);

        return "redirect:/";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage(Model model, @RequestParam String token) {
        model.addAttribute("token", token);
        // このメソッドでリセットページを表示するロジックを追加する
        return "/passReset/reset-password"; // HTMLページの名前
    }

    @PostMapping("/reset-password")
    public String resetPassword(Model model, @RequestParam String token, @RequestParam String newPassword, RedirectAttributes redirectAttributes) {
        boolean success = passwordResetService.resetPassword(token, newPassword);
        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Password reset successfully");
            return "redirect:/";
        } else {
            model.addAttribute("failureMessage", "Password reset failure");
            return "/passReset/reset-password";
        }
    }
}

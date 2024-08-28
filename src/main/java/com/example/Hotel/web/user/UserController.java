package com.example.Hotel.web.user;

import com.example.Hotel.domain.auth.UserEntity;
import com.example.Hotel.domain.auth.UserMapper;
import com.example.Hotel.domain.auth.UserService;
import com.example.Hotel.domain.top.HotelEntity;
import com.example.Hotel.web.process.ReserveForm;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/user")
@SessionAttributes(types = {UserForm.class})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserForm userForm;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpSession httpSession;

    @ModelAttribute(value = "userForm")
    public UserForm setUpUserForm(){
        return userForm;
    }

    @GetMapping("/signup")
    public String getSignUp(Model model, Locale locale, @ModelAttribute UserForm form) {

        return "user/userRegist";
    }

    @PostMapping("/signup")
    public String postSignup(Model model, Locale locale,
                             @ModelAttribute @Validated UserForm form, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
        if(bindingResult.hasErrors()) {
            // ユーザ登録画面に戻る
            return getSignUp(model, locale, form);
        }
        UserEntity user = modelMapper.map(form, UserEntity.class);

        userService.signup(user);
        sessionStatus.setComplete();
        //httpSession.removeAttribute("userForm");
        String successMessage = messageSource.getMessage("registration.success", null, locale);
        // 成功メッセージをFlashAttributesに追加
        redirectAttributes.addFlashAttribute("successMessage", successMessage);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    //@PostMapping("/login")
    //public String login(Model model,@ModelAttribute LoginForm loginForm) {

        //return "";
    //}

    @GetMapping("/logout")
    public String showLogoutForm() {
        return "logout";
    }

    @GetMapping("/edit")
    public String userEdit(Model model, @ModelAttribute("userForm") UserForm userForm) {
        model.addAttribute("userForm", userForm);

        return "user/userEdit";
    }


    @GetMapping("/detail")
    public String userdetail(Model model, Locale locale) {
        // ログインユーザ名を取得可能！
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        //後で処理組む DB等からユーザ情報を取得する処理
        UserEntity user = userMapper.findUser(userName).get();
        UserForm userForm = modelMapper.map(user, UserForm.class);
        model.addAttribute("userForm", userForm);
        return "user/detail";
    }

    @GetMapping("/modify")
    public String userModify(Model model, Locale locale) {
        // ログインユーザ名を取得可能！
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userMapper.findUser(userName).get();

        //System.out.println("_______________" + user.getAuthority().name());
        UserForm userForm = modelMapper.map(user, UserForm.class);
        model.addAttribute("userForm", userForm);
        return "user/modify";
    }

    @PostMapping("/modify")
    public String userPostModify(Model model, Locale locale, UserForm userForm) {
        // ログインユーザ名を取得可能！
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        int user_id = userMapper.findUser(userName).get().getId();

        UserEntity user = modelMapper.map(userForm, UserEntity.class);

        /* ユーザー情報更新 */
        userService.modify(user, user_id);

        return "redirect:/";
    }

    @GetMapping("/list")
    public String userList(Model model,
                           @PageableDefault(size = 5) Pageable pageable) {
        Page<UserEntity> pageList = userService.findAllUser(pageable);
        List<UserEntity> userList = pageList.getContent();
        UserForm form = new UserForm();

        model.addAttribute("page", pageList);
        model.addAttribute("userList", userList);
        model.addAttribute("userForm", form);

        return "user/userList";
    }

    /* 会員情報編集ページ*/
    @GetMapping("/edit/{id}")
    public String editUserInfo(Model model, @PathVariable("id") int id) {

        UserEntity user = userMapper.findUserId(id);
        UserForm userForm = modelMapper.map(user, UserForm.class);
        model.addAttribute("userForm", userForm);
        model.addAttribute("user", user);
        return "user/detail";
    }

    /* 会員情報編集ページより情報修正*/
    @PostMapping("/editPost")
    public String editPost(Model model,  @ModelAttribute("userForm") @Validated UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        UserEntity user = modelMapper.map(userForm, UserEntity.class);

        if(bindingResult.hasErrors()) {
            // ユーザ編集画面に戻る
            return userEdit(model, userForm);
        }

        userMapper.updateUserInfo(user);



        String successMessage = messageSource.getMessage("update.success", null, null);
        // 成功メッセージをFlashAttributesに追加
        redirectAttributes.addFlashAttribute("successMessage", successMessage);

        return "redirect:/";
    }





}





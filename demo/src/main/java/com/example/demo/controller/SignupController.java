package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.SignupForm;
import com.example.demo.service.UserApplicationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {
    @Autowired
    private UserApplicationService userApplicationService;

    /**
     * ユーザー登録画面
     */
    @GetMapping("/signup")
    public String getSignUp(Model model, @ModelAttribute SignupForm form) {
        // 性別を取得
        Map<String, Integer> genderMap = userApplicationService.getGenderMap();

        model.addAttribute("genderMap", genderMap);
        return "user/signup";
    }

    /**
     * ユーザー登録処理
     */
    @PostMapping("/signup")
    public String postSignup(@ModelAttribute @Validated SignupForm form, BindingResult result, Model model) {
        // 入力チェック
        if (result.hasErrors()) {
            // NGの場合ユーザー登録画面に戻る
            // return "signup";
            return getSignUp(model, form);
        }
        log.info(form.toString());
        // ログイン画面にリダイレクトする
        return "redirect:/login";
    }

}

package com.example.FinalProject.controllers;

import com.example.FinalProject.models.request.RegisterInfluencerRequest;
import com.example.FinalProject.models.response.LoginRequest;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.services.EmployeeService;
import com.example.FinalProject.services.LoginService;
import com.example.FinalProject.services.RegisterService;
import com.example.FinalProject.utils.GetAuthContext;
import javax.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
  
    private LoginService loginService;
    private EmployeeService employeeService;

    public LoginController(LoginService loginService, EmployeeService employeeService) {
        this.loginService = loginService;
        this.employeeService = employeeService;
    }

    @GetMapping("/login")
    public String login(LoginRequest loginRequest) {
        Authentication auth = GetAuthContext.getAuthorization();
         System.out.println("AUTH : " + auth.getPrincipal());
         System.out.println("AUTHORITIES : " + auth.getAuthorities());
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            return "loginNew";
    }
        return "redirect:/";
    }

    @GetMapping("/login/brand")
    public String loginBrand(LoginRequest loginRequest) {
        Authentication auth = GetAuthContext.getAuthorization();
        System.out.println("AUTH : " + auth.getPrincipal());
        System.out.println("AUTHORITIES : " + auth.getAuthorities());
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            return "loginBrand";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid LoginRequest request, BindingResult result) {
       System.out.println(request);
        if (result.hasErrors()) {
            return "loginNew";
        }
        if(!loginService.login(request)){
            return "redirect:/login?error=true";
        }
        return "redirect:/";
    }

    @PostMapping("/login/brand")
    public String loginBrand(@Valid LoginRequest request, BindingResult result) {
        System.out.println(request);
        if (result.hasErrors()) {
            return "loginBrand";
        }
        if(!loginService.loginBrand(request)){
            return "redirect:/login?error=true";
        }
        return "redirect:/";
    }
    
     @PostMapping("/logout")
    public String logout() {
        GetAuthContext.setAuthorization(null);
        return "redirect:/login?logout=true";
    }
    
    @GetMapping("/forgot_password")
    public String forgot_password() {
        return "forgot_password";
    }
    
//    @PostMapping("/forgot_password")
//    public @ResponseBody
//    ResponseMessage<ForgotPasswordRequest> forgot(@RequestBody ForgotPasswordRequest request) {
//        System.out.println("Lupa woyy");
//        return employeeService.forgotPassword(request);
//    }
    
//    @PostMapping("/forgot_password")
//    public String forgot(ForgotPasswordRequest forgotPasswordRequest,
//            BindingResult result, RedirectAttributes redirectAttributes) {
//        
//        if (result.hasErrors()) {
//            return "forgot_password";
//        }
//        
//        ResponseMessage<ForgotPasswordRequest> response = employeeService.forgotPassword(forgotPasswordRequest);
//        redirectAttributes.addFlashAttribute("message", response.getMessage());
//        
//        return "redirect:/login";
//    }

}

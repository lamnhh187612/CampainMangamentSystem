package com.company.client.Controller;

import com.company.client.Model.PassWordModel;
import com.company.client.Entity.VerificationToken;
import com.company.client.Event.RegistrationCompleteEvent;
import com.company.client.Entity.User;
import com.company.client.Model.UserModel;
import com.company.client.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result =userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verifies Successfully";
        }
        return "Bad User";

    }

    @PostMapping("/resetPassword")
    public String resetPassWord(@RequestBody PassWordModel passWordModel, HttpServletRequest request){
        User user=userService.findUserByEmail(passWordModel.getEmail());
        String url="";
        if(user!=null){
            String token = UUID.randomUUID().toString();
            userService.createPassWordResetTokenForUser(user,token);
            url=passwordResetTokenMail(user,applicationUrl(request),token);
        }
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token")String token,@RequestBody PassWordModel passWordModel){
        String result=userService.validatePasswordResetToken(token);
        if(!result.equalsIgnoreCase("valid")){
            return "Invalid Token";
        }
        Optional<User> user=userService.getUserByPasswordResetToken(token);
        if(user.isPresent()){
            userService.changePassword(user.get(), passWordModel.getNewPassword());
            return "Password Reset Successfully";
        }
        else {
            return "Invalid Token";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PassWordModel passWordModel){
        User user=userService.findUserByEmail(passWordModel.getEmail());
        if(!userService.checkIfValidOldPassword(user,passWordModel.getOldPassword())){
            return "Invalid Old Password";
        }

        //Save New Password
        userService.changePassword(user,passWordModel.getNewPassword());
        return "Password Change Successfully";
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url=applicationUrl+ "/savePassword?token="+token;
        //sendVerificationEmail()
        log.info(" Click the link to Reset your password : {}",url);
        return url;
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,HttpServletRequest request){
        VerificationToken verificationToken=userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user,applicationUrl(request),verificationToken);
        return "verification Link Sent";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url=applicationUrl+ "/verifyRegistration?token="+verificationToken.getToken();
        //sendVerificationEmail()
        log.info(" Click the link to verify your account : {}",url);
    }

    private String applicationUrl(HttpServletRequest request){
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}
package com.example.demo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.TokenAnnotation.PassToken;
import com.example.demo.TokenAnnotation.UserLoginToken;
import com.example.demo.config.TokenError;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    UserService userService;
    @Resource(name = "userServiceImpl")
    public void setUserService(UserService userService){
        this.userService=userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        Cookie[]cookies=request.getCookies();
        String token =null;
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                }
//                System.out.println("cookie:"+cookie.getName()+":"+cookie.getValue());
            }
        }
        if(token==null){
            String token_ = request.getHeader("Authorization");
            if(token_!=null){
                token=token_.split(" ")[1];
            }
        }
        if(!(object instanceof HandlerMethod)){
            return true;
            //此处有些懵
        }else{
            HandlerMethod handlerMethod = (HandlerMethod) object;
            Method method = handlerMethod.getMethod();
            if(method.isAnnotationPresent(PassToken.class)){
                PassToken passToken=method.getAnnotation(PassToken.class);
                if(passToken.required()){
                    return true;
                }
            }
            if(method.isAnnotationPresent(UserLoginToken.class)){
                UserLoginToken userLoginToken=method.getAnnotation(UserLoginToken.class);
                if(userLoginToken.required()){
                    if(token==null){
                        throw new TokenError("-4","token nullpoint error");
                    }
                    String userId;
                    try {
                        userId= JWT.decode(token).getAudience().get(0);
                    }catch (JWTDecodeException e){
                        throw new TokenError("-3","token decode error!");
                    }
                    User user = this.userService.selectUserById(Integer.valueOf(userId));
                    if(user==null){
                        throw new TokenError("-2","user nullpoint error");
                    }
                    JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                    try{
                        jwtVerifier.verify(token);
                        return true;
                    }catch (JWTVerificationException e){
                        throw new TokenError("-1","token verified error!");
                    }
                }
            }
            return true;
        }
    }










}

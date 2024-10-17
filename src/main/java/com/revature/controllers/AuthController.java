package com.revature.controllers;

import com.revature.models.LoginDTO;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class AuthController {

    public static HttpSession ses;

    public Handler loginHandler = ctx -> {
        LoginDTO lDTO = ctx.bodyAsClass(LoginDTO.class);

        //Employee loggedInEmployee = aDAO.login(lDTO.getAdmin_id(), lDTO.getFirst_name());
        if(lDTO != null) {
            //create a session object
            ses = ctx.req().getSession();

            //store user info with set attribute method
            ses.setAttribute("first_name", lDTO.getFirst_name());

            //send success message
            ctx.status(200);
            ctx.result("Login Successful! Welcome, " + ses.getAttribute("first_name"));
        } else {
            //if login fails status 401 -
            ctx.status(401);
            ctx.result("Login failed! Try again");
        }

    };
}

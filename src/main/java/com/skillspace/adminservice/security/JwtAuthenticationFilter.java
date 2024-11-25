package com.skillspace.adminservice.security;

import com.skillspace.adminservice.service.AdminService;
import com.skillspace.adminservice.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AdminService adminService;

    JwtAuthenticationFilter(JwtService jwtService, AdminService adminService) {
        this.jwtService = jwtService;
        this.adminService = adminService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.print("""
                    {
                        "Message":"Invalid Header"
                    }
                    """);

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        else{
            String token = header.replace("Bearer ", "");

            if(jwtService.tokenStatus(token).isValid()){
                if(!jwtService.tokenStatus(token).isExpired()){

                    if(jwtService.extractRole(token).equals("admin")){
                        String username = jwtService.extractUsername(token);
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(username, null, null);
                        SecurityContextHolder.getContext().setAuthentication(authToken);

                        if(adminService.getEmailStatus(username)){
                            filterChain.doFilter(request, response);
                        }
                        else{
                            response.setContentType("application/json");
                            PrintWriter writer = response.getWriter();
                            writer.print("""
                            {
                                "emailStatus":"""+false+"""
                            }
                            """);

                            response.setStatus(HttpServletResponse.SC_GONE);

                        }



                    }
                    else{
                        response.setContentType("application/json");
                        PrintWriter writer = response.getWriter();
                        writer.print("""
                    {
                        "Message":"Unauthorized"
                    }
                    """);

                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);


                    }



                }
                else{
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    writer.print("""
                    {
                        "Message":"Expired Token"
                    }
                    """);

                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
            else{
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.print("""
                    {
                        "Message":"Invalid Token"
                    }
                    """);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}

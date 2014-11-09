package com.projectx.mvc.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.projectx.mvc.domain.CustomerDocumetDTO;
import com.projectx.mvc.services.CustomerQuickRegisterService;

public class ImageServlet {

	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String imageId = request.getParameter("id");

        if (imageId == null) {
        
        	response.sendError(HttpServletResponse.SC_NOT_FOUND); 
            return;
        }

        CustomerDocumetDTO image = customerQuickRegisterService.getCustomerDocumetById(Long.parseLong(imageId)) ;

        if (image == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }


        response.reset();
       // response.setContentType(image.getContentType());
       // response.setContentLength(image.getContent().length);

       response.getOutputStream().write(image.getImage());
    }
	
	
	
}

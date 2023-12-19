package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;

@Controller
public class AccountController {
	
	@Autowired
	AccountDAO dao;
	
	
	
	@RequestMapping("/forgetPassword")
	public String forgetHome(Model model) {
		
		return "forget";
	}
	@PostMapping("/forgetPassword/success")
	public String forgetPassword(Model model, @RequestParam("email") String email) {
//		if(email != null) {
//			MailInfo mail = new MailInfo();
//			Account account = dao.getAccountByEmail(email);
//			
//			
//			// random mk moi
//			double randomDouble = Math.random();
//            randomDouble = randomDouble * 1000 + 1;
//            int randomInt = (int) randomDouble;
//            
//            account.setPassword(String.valueOf(randomInt));
//            
//			mail.setTo(email);
//			mail.setSubject("Khôi phục mật khẩu thành công");
//			// Tạo nội dung email
//			StringBuilder bodyBuilder = new StringBuilder();
//			bodyBuilder.append("Mật khẩu đã được reset. Đây là thông tin tài khoản của bạn").append("<br><br>");
//			
//			// Tạo bảng với CSS
//			bodyBuilder.append("<table style=\"border-collapse: collapse;\">");
//			bodyBuilder.append(
//					"<tr><th style=\"border: 1px solid black; padding: 8px;\">Fullname</th>"
//					+ "<th style=\"border: 1px solid black; padding: 8px;\">Username</th>"
//					+ "<th style=\"border: 1px solid black; padding: 8px;\">Password</th></tr>");
//
//			// Lấy thông tin chi tiết của từng sản phẩm trong giỏ hàng và thêm vào bảng
//				bodyBuilder.append("<tr>");
//				bodyBuilder.append("<td style=\"border: 1px solid black; padding: 8px; text-align: center;\">")
//						.append(account.getFullname()).append("</td>");
//				bodyBuilder.append("<td style=\"border: 1px solid black; padding: 8px; text-align: center;\">")
//						.append(account.getUsername()).append("</td>");
//				bodyBuilder.append("<td style=\"border: 1px solid black; padding: 8px; text-align: center;\">")
//						.append(account.getPassword()).append("</td>");
//				bodyBuilder.append("</tr>");
//		
//
//			bodyBuilder.append("</table>");
//			mail.setBody(bodyBuilder.toString());
//			
//			dao.save(account);
//
//			mailerService.queue(mail);
//		}
		return "redirect:/login";
	}
}
package com.bookory.server.helper;

import java.sql.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	 private final String secret = "ABCDEFGHFIJKLMNOPQRSTUVXYZ0123456789ABCDEFGHFIJKLMNOPQRSTUVXYZ0123456789BookStoreSpringBoot"; // secret key bí mật để tạo và xác thực JWT

	    private final long validityInMilliseconds = 3600000; // thời gian hết hạn của JWT (1 giờ)

	    public String createToken(String username) {
	        // Tạo JWT bằng cách sử dụng builder pattern
	        return Jwts.builder()
	                .setSubject(username) // đặt username vào phần subject của JWT
	                .setIssuedAt(new Date(System.currentTimeMillis())) // đặt thời gian tạo JWT là thời gian hiện tại
	                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds)) // đặt thời gian hết hạn của JWT
	                .signWith(SignatureAlgorithm.HS256, secret) // đặt thuật toán mã hóa và secret key để tạo chữ ký cho JWT
	                .compact(); // kết thúc việc tạo JWT và trả về chuỗi JWT đã được mã hóa
	    }
}

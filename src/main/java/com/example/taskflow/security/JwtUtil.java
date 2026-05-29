package com.example.taskflow.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Kunci Rahasia untuk menyegel Token (Harus panjang dan aman)
    private static final String SECRET_KEY = "IniAdalahKunciRahasiaYangSangatPanjangSekaliUntukTaskFlowApi123!";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Masa berlaku Token (Contoh: 1 Jam = 3.600.000 milidetik)
    private static final long EXPIRATION_TIME = 3600000;

    // Fungsi 1: Mencetak Token baru saat User berhasil Login
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Fungsi 2: Membaca siapa pemilik Token ini
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Fungsi 3: Memastikan Token ini asli dan belum kedaluwarsa
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
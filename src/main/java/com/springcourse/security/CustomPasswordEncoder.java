package com.springcourse.security;

import com.springcourse.service.util.HashUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return HashUtil.getSecureHash(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        String hash = HashUtil.getSecureHash(charSequence.toString());
        return hash.equals(encodedPassword);
    }
}

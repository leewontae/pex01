package org.zerock.security;/*
package org.zerock.security;

import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence rawPassword){
        log.warn("before encode : " + rawPassword);
        return rawPassword.toString();
    }

    public boolean matches(CharSequence rewPassword, String encodedPassword){
        log.warn("matches: " + rewPassword + " : " + encodedPassword);
        return rewPassword.toString().equals(encodedPassword);
    }
}
*/

package org.zerock.security.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.vo.MemberVo;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class CustomUser extends User {
//MemberVo 를 UsersDetails 타입으로 변환하기
    //membervo 클래스를 직접 수정해서 UserDeTailes 인터페이스를 구현하도록 하는 방법도 있지만
    // 가능하면 기존의 클래스를 수정하지 않고 확장하는 방식이 더 낫다.
    // CustomUser은 User을 상속 했으며 부모 클래스의 생성자를 호출해야만 객체를 생성 할수 있다.
    private static final long serialVersionUID =1L;
    private MemberVo member;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(MemberVo vo){

        super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream().map(authVO -> new SimpleGrantedAuthority(authVO.getAuth())).collect(Collectors.toList()));
//membervo를 파라미터로 전달해서 User 클래스에 맞게 생성자를 호출한다.
        // authVO는 GrantedAuthority 객체로 변환해야 하므로 steam() 과 map()을 이용해서 처리한다.
        this.member =vo;
    }
}

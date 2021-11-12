package org.zerock.security;


import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.mapper.MemberMapper;
import org.zerock.security.domain.CustomUser;
import org.zerock.vo.MemberVo;

@Log4j
public class CustomUserDatailsService implements UserDetailsService {

    @Setter(onMethod_= {@Autowired})
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

    log.warn("Load User By UserName : " + userName );

    //username means userid
        MemberVo vo = memberMapper.read(userName);

        log.warn("queried by member mapper : "+ vo);

        return vo ==null ? null : new CustomUser(vo);
        //이 loadUserByUsername 메소드는 내부적으로 memberVO를 조회하고 , 만일 MemberVo의 인스턴스를 얻을수 있다면 CustomUser타입의 객체로 변환해서 반환한다.
    }
}

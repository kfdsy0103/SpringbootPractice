package board.board.repository;

import board.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@aaa.com")
                    .password("1111")
                    .name("USER" + i)
                    .build();

            memberRepository.save(member);
            // save 시 select 가 실행되는 이유
            // @Id 필드에 값을 넣어서 저장할 경우 jpa 내부적으로 새로운 엔티티가 아닌것으로 인식해
            // save() 메서드가 merge()를 호출한다.
            // merge()는 detached 상태를 managed 상태로 만들기 위해 식별자로 select를 한 후,
            // 조회된 객체가 있으면 update, 없으면 insert 쿼리를 날린다.
        });
    }
}

package board.board.repository;

import board.board.entity.Member;
import board.board.entity.MovieMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieMemberRepositoryTests {

    @Autowired
    private MovieMemberRepository memberRepository;

    @Autowired
    private MovieReviewRepository reviewRepository;

    @Test
    public void insertMember() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            MovieMember member = MovieMember.builder()
                    .email("r" + i + "@naver.com")
                    .password("1111")
                    .nickname("reviewer" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteMember() {
        Long mid = 1L;

        MovieMember member = MovieMember.builder().mid(mid).build();

        /*
            memberRepository.deleteById(mid);
            reviewRepository.deleteByMember(member);
            순서 주의, fk 삭제 후 pk 삭제
        */

        reviewRepository.deleteByMember(member); // delete 쿼리가 댓글 수 만큼 나간다.
        memberRepository.deleteById(mid);
    }
}
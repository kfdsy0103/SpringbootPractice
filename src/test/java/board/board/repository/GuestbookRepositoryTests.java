package board.board.repository;

import board.board.entity.Guestbook;
import board.board.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title...." + i)
                    .content("Content...." + i)
                    .writer("user" + (i % 10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest() {
        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if(result.isPresent()) {
            Guestbook guestbook = result.get();

            guestbook.changeTitle("Changed Title...");
            guestbook.changeContent("Changed Content...");

            guestbookRepository.save(guestbook);
        }
    }

    @Test
    public void testQuery1() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook; // 1 : 동적 처리를 위해 Q도메인을 얻어옴. 필드를 변수로 사용 가능
        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder(); // 2 : BooleanBuilder는 Where에 들어갈 조건절을 만드는 컨테이너

        BooleanExpression expression = qGuestbook.title.contains(keyword); // 3 : 원하는 조건을 필드 값과 결합해 생성. Predicate 타입이어야 빌더에 들어감

        builder.and(expression); // 4 : 만든 조건을 and, or 키워드로 결합

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable); // 5 : QuerydslPredicateExcutor 인터페이스의 findAll을 사용하여 검색

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery2() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
        BooleanExpression exContent = qGuestbook.content.contains(keyword);
        BooleanExpression exGno = qGuestbook.gno.gt(0L);

        BooleanExpression exAll = exTitle.or(exContent).and(exGno); // 조건 결합

        builder.and(exAll); // 생성된 조건을 빌더에 전달

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });

    }
}

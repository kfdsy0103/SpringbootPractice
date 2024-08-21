package board.board.repository;

import board.board.entity.Movie;
import board.board.entity.MovieMember;
import board.board.entity.MovieReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieReviewRepositoryTests {

    @Autowired
    private MovieReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {
        // 100개의 리뷰 등록
        IntStream.rangeClosed(1, 100).forEach(i -> {

            // 영화 번호
            long mno = (long) (Math.random() * 100) + 1;
            Movie movie = Movie.builder().mno(mno).build();

            // 리뷰어 번호
            long mid = (long) (Math.random() * 100) + 1;
            MovieMember member = MovieMember.builder().mid(mid).build();

            MovieReview review = MovieReview.builder()
                    .member(member)
                    .movie(movie)
                    .grade((int) (Math.random() * 5) + 1)
                    .text("느낀점 ... " + i)
                    .build();

            reviewRepository.save(review);
        });
    }

    @Test
    public void testGetMovieReviews() {
        Movie movie = Movie.builder().mno(94L).build();

        List<MovieReview> result = reviewRepository.findByMovie(movie);

        result.forEach(review -> {
            System.out.print(review.getRno());
            System.out.print("/t"+review.getGrade());
            System.out.print("/t"+review.getText());
            System.out.print("/t"+review.getMember().getEmail());
            System.out.println("-------------------------");
        });
    }
}
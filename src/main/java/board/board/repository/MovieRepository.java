package board.board.repository;

import board.board.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m, i, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
            "left join MovieReview r on r.movie = m " +
            "left join MovieImage i on i.movie = m " +
            "group by m")
    Page<Object[]> getListPage(Pageable pageable); // 페이지 처리

    @Query("select m, i, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
            "left join MovieReview r on r.movie = m " +
            "left join MovieImage i on i.movie = m " +
            "where m.mno = :mno " +
            "group by i")
    List<Object[]> getMovieWithAll(@Param("mno") Long mno); // 특정 영화 조회 (이미지 개수만큼 데이터 반환)
}

/*
    // getListPage 를 QueryDSL 로 작성 시

    QMovie movie = QMovie.movie;
    QMovieReview review = QMovieReview.movieReview;
    QMovieImage movieImage = QMovieImage.movieImage;

    JPQLQuery<Movie> jpqlQuery = from(movie);
    jpqlQuery.leftJoin(movieImage).on(movieImage.movie.eq(movie));
    jpqlQuery.leftJoin(review).on(review.movie.eq(movie));

    JPQLQuery<Tuple> tuple = jpqlQuery.select(movie, movieImage, review.grade.avg(), review.countDistinct());

    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = movie.mno.gt(0L);

    booleanBuilder.and(expression);
    tuple.where(booleanBuilder);

    tuple.fetch();

    ...
 */
package board.board.repository;

import board.board.entity.Movie;
import board.board.entity.MovieMember;
import board.board.entity.MovieReview;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

    // 로딩 설정을 변경할 필드명
    @EntityGraph(attributePaths = "member", type = EntityGraph.EntityGraphType.FETCH)
    List<MovieReview> findByMovie(Movie movie);

    @Modifying // 필수 어노테이션
    @Query("delete from MovieReview r where r.member = :member")
    void deleteByMember(@Param("member") MovieMember member);
}
// 즉시 로딩하는 방법
// 1. JPQL 에서 Fetch Join 작성
// 2. EntityGraph 로 로딩 설정을 Fetch 변경
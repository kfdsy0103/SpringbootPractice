package board.board.repository;

import board.board.entity.MovieMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieMemberRepository extends JpaRepository<MovieMember, Long> {
}

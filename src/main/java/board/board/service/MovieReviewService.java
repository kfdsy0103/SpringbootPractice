package board.board.service;

import board.board.dto.MovieReviewDTO;
import board.board.entity.Movie;
import board.board.entity.MovieMember;
import board.board.entity.MovieReview;

import java.util.List;

public interface MovieReviewService {
    
    // 영화의 모든 리뷰 가져오기
    List<MovieReviewDTO> getListOfMovie(Long mno);
    
    // 영화 리뷰 추가
    Long register(MovieReviewDTO movieReviewDTO);
    
    // 영화 리뷰 수정
    void modify(MovieReviewDTO movieReviewDTO);
    
    // 영화 리뷰 삭제
    void remove(Long rno);

    default MovieReview dtoToEntity(MovieReviewDTO movieReviewDTO) {

        MovieReview movieReview = MovieReview.builder()
                .rno(movieReviewDTO.getRno())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(MovieMember.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();

        return movieReview;
    }

    default MovieReviewDTO entityToDto(MovieReview movieReview) {
        MovieReviewDTO movieReviewDTO = MovieReviewDTO.builder()
                .rno(movieReview.getRno())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getMid())
                .nickname(movieReview.getMember().getNickname())
                .email(movieReview.getMember().getEmail())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate(movieReview.getModDate())
                .build();

        return movieReviewDTO;
    }

}

package board.board.service;

import board.board.dto.MovieReviewDTO;
import board.board.entity.Movie;
import board.board.entity.MovieReview;
import board.board.repository.MovieReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieReviewServiceImpl implements MovieReviewService {

    private final MovieReviewRepository reviewRepository;

    @Override
    public List<MovieReviewDTO> getListOfMovie(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        List<MovieReview> result = reviewRepository.findByMovie(movie);
        return result.stream().map(movieReview -> entityToDto(movieReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(MovieReviewDTO movieReviewDTO) {
        MovieReview movieReview = dtoToEntity(movieReviewDTO);

        reviewRepository.save(movieReview);

        return movieReview.getRno();
    }

    @Override
    public void modify(MovieReviewDTO movieReviewDTO) {
        Optional<MovieReview> result = reviewRepository.findById(movieReviewDTO.getRno());

        if(result.isPresent()) {
            MovieReview movieReview = result.get();
            movieReview.changeGrade(movieReviewDTO.getGrade());
            movieReview.changeText(movieReviewDTO.getText());
            reviewRepository.save(movieReview);
        }
    }

    @Override
    public void remove(Long rno) {
        reviewRepository.deleteById(rno);
    }
}

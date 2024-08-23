package board.board.service;

import board.board.dto.MovieDTO;
import board.board.dto.PageRequestDTO;
import board.board.dto.PageResultDTO;
import board.board.entity.Movie;
import board.board.entity.MovieImage;
import board.board.repository.MovieImageRepository;
import board.board.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieImageRepository imageRepository;

    @Override
    public MovieDTO getMovie(Long mno) {
        List<Object[]> result = movieRepository.getMovieWithAll(mno);

        Movie movie = (Movie)result.get(0)[0]; // Movie 엔티티는 모든 ROW가 동일한 값을 가짐

        List<MovieImage> movieImageList = new ArrayList<>();

        result.forEach(arr -> {
            movieImageList.add((MovieImage) arr[1]);
        });

        Double avg = (Double) result.get(0)[2]; // 마찬가지로 평점도 모든 ROW가 동일한 값을 가짐
        Long reviewCnt = (Long) result.get(0)[3]; // 리뷰 개수

        return entitiesToDTO(movie, movieImageList, avg, reviewCnt);
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO((Movie) arr[0], List.of((MovieImage) arr[1]), (Double) arr[2], (Long) arr[3]));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    @Transactional
    public Long register(MovieDTO movieDTO) {
        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });

        return movie.getMno();
    }
}

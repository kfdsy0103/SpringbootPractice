package board.board.controller;

import board.board.dto.MovieReviewDTO;
import board.board.service.MovieReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final MovieReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<MovieReviewDTO>> getList(@PathVariable("mno") Long mno) {
        log.info("-------------list-------------");
        log.info("MNO: " + mno);

        List<MovieReviewDTO> result = reviewService.getListOfMovie(mno);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody MovieReviewDTO movieReviewDTO) {
        log.info("-------------add-------------");
        log.info("reviewDTO: " + movieReviewDTO);

        Long rno = reviewService.register(movieReviewDTO);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{rno}")
    public ResponseEntity<Long> modifyReview(@PathVariable("rno") Long rno, @RequestBody MovieReviewDTO movieReviewDTO) {
        log.info("-------------modify MovieReview-------------");
        log.info("reviewDTO: " + movieReviewDTO);

        reviewService.modify(movieReviewDTO);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{rno}")
    public ResponseEntity<Long> removeReview(@PathVariable("rno") Long rno) {
        log.info("-------------remove MovieReview-------------");
        log.info("reviewnum: " + rno);

        reviewService.remove(rno);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }
}

package board.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieReviewDTO {

    // review의 id
    private Long rno;

    // movie의 id
    private Long mno;

    // member의 정보
    private Long mid;
    private String nickname;
    private String email;

    private int grade;

    private String text;

    private LocalDateTime regDate, modDate;
}

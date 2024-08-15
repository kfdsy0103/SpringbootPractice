package board.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bno;

    private String title;

    private String content;

    private String writerEmail; // 이메일

    private String writerName; // 이름

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private int replyCount;
}

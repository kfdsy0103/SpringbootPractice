package board.board.service;

import board.board.dto.BoardDTO;
import board.board.dto.PageRequestDTO;
import board.board.dto.PageResultDTO;
import board.board.entity.Board;
import board.board.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO); // 게시글 목록 처리
    
    BoardDTO get(Long bno); // 게시글 조회 처리

    void removeWithReplies(Long bno); // 삭제 기능 (댓글 삭제 -> 게시글 삭제) 하나의 트랜잭션에서 이루어짐

    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder().bno(dto.getBno()).title(dto.getTitle()).content(dto.getContent()).writer(member).build();

        return board;
    }

    default BoardDTO entityToDto(Board board, Member member, Long replyCount) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return boardDTO;
    }
}

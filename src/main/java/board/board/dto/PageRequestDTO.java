package board.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page;
    private int size;

    private String type;
    private String keyword;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
    // JPA에서 사용하는 Pageable을 생성하는 것이 목적
}
// 목록 페이지 요청 시, 페이지 번호/개수/검색 조건 등 목록 관련 데이터를 재사용하기 위한 클래스
// 서비스 계층 -> 리포지토리 계층으로 전달 시 사용
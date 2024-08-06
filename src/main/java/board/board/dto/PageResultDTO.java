package board.board.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {

    private List<DTO> dtoList;

    private int totalPage; // 총 페이지 사이즈

    private int page; // 현재 페이지
    private int size; // 현재 페이지 사이즈

    private int start, end; // 시작, 끝 번호
    private boolean prev, next; // 버튼 유무

    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        // 페이지의 끝번호 공식(10개씩 보여질 경우)
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9; // 끝번호를 구하면 시작번호는 쉽게 구할 수 있음

        prev = start > 1;
        end = tempEnd < totalPage ? tempEnd : totalPage;
        next = tempEnd < totalPage;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }


}
// 리포지토리 -> 서비스 계층으로 전달 시 사용
// 지네릭으로 선언하여 확장성을 높임. 어떤 종류의 Page<EN>이 생성되더라도 이 클래스를 통해 처리할 수 있음
// fn은 EN 타입을 DTO 타입으로 변환해주는 기능을 함
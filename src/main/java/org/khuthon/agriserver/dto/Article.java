package org.khuthon.agriserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {
    private int id;
    private int boardId;           // 어떤 게시판에 속하는지
    private String title;          // 제목
    private String content;        // 내용
    private LocalDateTime createdAt; // 작성 시간
    private int popularity;        // 추천수
    private String imageUrl;       // 이미지 URL
    private int writerId;          // 작성자 ID
    private String writerName;     // 작성자 이름
}

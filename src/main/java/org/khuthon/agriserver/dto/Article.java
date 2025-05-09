package org.khuthon.agriserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
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

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }
    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public int getBoardId() {
        return boardId;
    }
    public String getContent() {
        return content;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public int getId() {
        return id;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public int getPopularity() {
        return popularity;
    }
    public String getTitle() {
        return title;
    }
    public int getWriterId() {
        return writerId;
    }
    public String getWriterName() {
        return writerName;
    }
}

package org.khuthon.agriserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.khuthon.agriserver.dto.Article;

import java.util.List;

@Mapper
public interface ArticleDao {
    List<Article> getArticlesByBoardId(int boardId);  // 게시판 ID에 해당하는 모든 게시글
    List<Article> getArticlesByPopularity();          // 추천수로 정렬된 게시글
    List<Article> getArticlesByTime();                // 최신순으로 정렬된 게시글

    void insertArticle(int userId, String userName, int boardId, String title, String content, String imageUrl);              // 게시글 등록
    void increasePopularity(int id);                  // 추천수 증가
    void decreasePopularity(int id);                  // 추천수 감소
}


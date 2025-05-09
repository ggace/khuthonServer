package org.khuthon.agriserver.service;

import org.khuthon.agriserver.dao.ArticleDao;
import org.khuthon.agriserver.dto.Article;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    // 특정 게시판의 게시글들 가져오기
    public List<Article> getArticlesByBoardId(int boardId) {
        return articleDao.getArticlesByBoardId(boardId);
    }

    // 추천수로 정렬된 게시글들
    public List<Article> getArticlesByPopularity() {
        return articleDao.getArticlesByPopularity();
    }

    // 최신순으로 정렬된 게시글들
    public List<Article> getArticlesByTime() {
        return articleDao.getArticlesByTime();
    }

    // 게시글 등록
    public void createArticle(int userId, String userName, int boardId, String title, String content, String imageUrl) {
        Article article = new Article();
        article.setBoardId(boardId);
        article.setTitle(title);
        article.setContent(content);
        article.setCreatedAt(LocalDateTime.now());
        article.setPopularity(0);
        article.setImageUrl(imageUrl);
        article.setWriterId(userId);
        article.setWriterName(userName);

        articleDao.uploadArticle(article);
    }

    // 추천수 증가
    public void increasePopularity(int id) {
        articleDao.increasePopularity(id);
    }

    // 추천수 감소
    public void decreasePopularity(int id) {
        articleDao.decreasePopularity(id);
    }
}


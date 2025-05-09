package org.khuthon.agriserver.service;

import java.util.List;

import org.khuthon.agriserver.dao.BoardDao;
import org.khuthon.agriserver.dao.ArticleDao;
import org.khuthon.agriserver.dto.Board;
import org.khuthon.agriserver.dto.Article;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class BoardService {

    private final BoardDao boardDao;
    private final ArticleDao articleDao;

    public BoardService(BoardDao boardDao, ArticleDao articleDao) {
        this.boardDao = boardDao;
        this.articleDao = articleDao;
    }

    // 모든 게시판 조회
    public List<Board> getAllBoards() {
        return boardDao.getAllBoards();
    }

    // 특정 게시판의 게시글 조회
    public List<Article> getArticlesByBoardId(int boardId) {
        return articleDao.getArticlesByBoardId(boardId);
    }

    // 추천수로 정렬된 게시글 조회
    public List<Article> getArticlesByPopularity() {
        return articleDao.getArticlesByPopularity();
    }

    // 최신순으로 정렬된 게시글 조회
    public List<Article> getArticlesByTime() {
        return articleDao.getArticlesByTime();
    }

    // 게시글 등록
    public boolean createArticle(int userId, String userName, int boardId, String title, String content, String imageUrl) {
        try {
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 게시글 추천수 증가
    public boolean increasePopularity(int id) {
        try {
            articleDao.increasePopularity(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 게시글 추천수 감소
    public boolean decreasePopularity(int id) {
        try {
            articleDao.decreasePopularity(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


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

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    // 모든 게시판 조회
    public List<Board> getAllBoards() {
        return boardDao.getAllBoards();
    }

    // 게시판 생성
    public boolean createBoard(String name, String description) {
        try {
            Board board = new Board();
            board.setName(name);
            board.setDescription(description);
            boardDao.insertBoard(board);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


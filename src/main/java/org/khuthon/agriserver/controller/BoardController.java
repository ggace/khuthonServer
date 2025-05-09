package org.khuthon.agriserver.controller;

import org.khuthon.agriserver.dto.Article;
import org.khuthon.agriserver.dto.Board;
import org.khuthon.agriserver.dao.BoardDao;
import org.khuthon.agriserver.dao.ArticleDao;
import org.khuthon.agriserver.service.BoardService;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/board")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    // 모든 게시판 조회
    @GetMapping("/")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    // 게시판 생성
    @PostMapping("/create")
    public String createBoard(@RequestParam String name, @RequestParam String description) {
        if (boardService.createBoard(name, description)) {
            return "Board successfully created";
        }
        return "Failed to create board";
    }
}

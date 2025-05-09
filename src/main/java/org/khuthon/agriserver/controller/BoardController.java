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

    // 게시판 목록 조회
    @GetMapping("/")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    // 게시글 목록 조회 (특정 게시판)
    @GetMapping("/{boardId}/articles")
    public List<Article> getArticlesByBoardId(@PathVariable int boardId) {
        return boardService.getArticlesByBoardId(boardId);
    }

    // 추천수로 정렬된 게시글 조회
    @GetMapping("/popular")
    public List<Article> getPopularArticles() {
        return boardService.getArticlesByPopularity();
    }

    // 최신순으로 정렬된 게시글 조회
    @GetMapping("/recent")
    public List<Article> getRecentArticles() {
        return boardService.getArticlesByTime();
    }

    // 게시글 등록 (제목, 내용, 이미지 포함)
    @PostMapping("/upload")
    public String uploadArticle(HttpServletRequest request,
                                @RequestParam("boardId") int boardId,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content,
                                @RequestParam("image") MultipartFile image) throws IOException {
        // 로그인 체크
        if (request.getAttribute("isLogined") == null || !(boolean) (request.getAttribute("isLogined"))) {
            return "not logined";
        }

        int userId = (int) request.getAttribute("loginedId");
        String userName = (String) request.getAttribute("loginedName");

        // 이미지 저장
        String uploadDir = "uploads/";
        String originalFilename = image.getOriginalFilename();
        String savedFilename = UUID.randomUUID() + "-" + originalFilename;
        Path imagePath = Paths.get(uploadDir, savedFilename);

        Files.createDirectories(imagePath.getParent());
        image.transferTo(imagePath.toFile());

        String imageUrl = "/uploads/" + savedFilename;

        // 게시글 등록
        boardService.createArticle(userId, userName, boardId, title, content, imageUrl);

        return "successfully uploaded";
    }

    // 게시글 추천수 증가
    @PostMapping("/{id}/like")
    public String increasePopularity(@PathVariable int id) {
        boardService.increasePopularity(id);
        return "popularity increased";
    }

    // 게시글 추천수 감소
    @PostMapping("/{id}/dislike")
    public String decreasePopularity(@PathVariable int id) {
        boardService.decreasePopularity(id);
        return "popularity decreased";
    }
}

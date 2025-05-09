package org.khuthon.agriserver.controller;

import org.khuthon.agriserver.dto.Article;
import org.khuthon.agriserver.service.ArticleService;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 게시글 목록 조회 (게시판별)
    @GetMapping("/{boardId}")
    public List<Article> getArticlesByBoardId(@PathVariable int boardId) {
        return articleService.getArticlesByBoardId(boardId);
    }

    // 추천수로 정렬된 게시글 조회
    @GetMapping("/popular")
    public List<Article> getPopularArticles() {
        return articleService.getArticlesByPopularity();
    }

    // 최신순으로 정렬된 게시글 조회
    @GetMapping("/recent")
    public List<Article> getRecentArticles() {
        return articleService.getArticlesByTime();
    }

    // 게시글 등록 (이미지 포함)
    @PostMapping("/upload")
    public String uploadArticle(HttpServletRequest request,
                                @RequestParam("boardId") int boardId,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content,
                                @RequestParam("image") MultipartFile image) throws IOException {
        // 로그인 체크
        if (!(boolean) request.getAttribute("isLogined")) {
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
        articleService.createArticle(userId, userName, boardId, title, content, imageUrl);

        return "successfully uploaded";
    }

    // 추천수 증가
    @PostMapping("/{id}/like")
    public String increasePopularity(@PathVariable int id) {
        articleService.increasePopularity(id);
        return "popularity increased";
    }

    // 추천수 감소
    @PostMapping("/{id}/dislike")
    public String decreasePopularity(@PathVariable int id) {
        articleService.decreasePopularity(id);
        return "popularity decreased";
    }
}

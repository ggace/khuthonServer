// const boardApiUrl = 'http://172.21.12.44:5500/api/board/'; // 기존 URL 주석 처리 또는 삭제
const baseApiUrl = 'http://172.21.12.44:5500/api'; // 기본 API 경로
const recentArticlesUrl = `${baseApiUrl}/article/recent`;
const popularArticlesUrl = `${baseApiUrl}/article/popular`;
const noticeArticlesUrl = `${baseApiUrl}/article/1`; // 공지 (boardId: 1)
const freeArticlesUrl = `${baseApiUrl}/article/2`;   // 자유 (boardId: 2)
const infoArticlesUrl = `${baseApiUrl}/article/3`;   // 정보 (boardId: 3)

const postListContainer = document.getElementById('post-list-container');
const activeTabStorageKey = 'boardActiveTab';

// 카테고리명과 boardId 매핑 (서버 API 명세에 따라 실제 ID로 수정 필요)
const categoryBoardIdMap = {
    '공지': 1,
    '자유': 2,
    '정보': 3,
    // '인기'와 '최신'은 특정 boardId가 아닐 수 있음
};

function formatDateTime(dateTimeString) {
    if (!dateTimeString) return '날짜 정보 없음';
    try {
        const date = new Date(dateTimeString);
        return `${date.getFullYear()}.${String(date.getMonth() + 1).padStart(2, '0')}.${String(date.getDate()).padStart(2, '0')} ${String(date.getHours()).padStart(2, '0')}:${String(date.getMinutes()).padStart(2, '0')}`;
    } catch (e) {
        console.error('날짜 포맷 변경 오류:', e);
        return dateTimeString;
    }
}

async function fetchAndDisplayPosts(apiUrl = boardApiUrl) {
    if (!postListContainer) {
        console.error('게시글 목록 컨테이너를 찾을 수 없습니다.');
        return;
    }
    postListContainer.innerHTML = '<p style="text-align:center;">게시글을 불러오는 중입니다...</p>';

    try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error(`API 요청 실패: ${response.status} ${response.statusText}`);
        }
        const articles = await response.json();
        postListContainer.innerHTML = ''; 

        if (articles && articles.length > 0) {
            articles.forEach(article => {
                const postLink = document.createElement('a');
                postLink.href = `post-detail.html?id=${article.id}`;
                postLink.classList.add('post-list-item-link');
                postLink.dataset.category = article.boardId || '기타'; 
                postLink.dataset.popularity = article.popularity || 0;

                const listItem = document.createElement('div');
                listItem.classList.add('post-list-item');

                const mainDiv = document.createElement('div');
                mainDiv.classList.add('post-item-main');
                
                const titleSpan = document.createElement('span');
                titleSpan.classList.add('title');
                titleSpan.textContent = article.title || '제목 없음';
                mainDiv.appendChild(titleSpan);

                const metaSpan = document.createElement('span');
                metaSpan.style.fontSize = '0.8em';
                metaSpan.style.color = '#555';
                metaSpan.style.marginTop = '5px';
                metaSpan.textContent = `작성자: ${article.writerName || '익명'} | 작성일: ${formatDateTime(article.createdAt)} | 추천: ${article.popularity || 0}`;
                mainDiv.appendChild(metaSpan);
                listItem.appendChild(mainDiv);

                if (article.imageUrl) {
                    const photoDiv = document.createElement('div');
                    photoDiv.classList.add('photo-placeholder');
                    photoDiv.style.backgroundImage = `url('${article.imageUrl}')`;
                    photoDiv.textContent = ''; 
                    listItem.appendChild(photoDiv);
                } else {
                    const photoDiv = document.createElement('div');
                    photoDiv.classList.add('photo-placeholder');
                    photoDiv.textContent = '사진X';
                    listItem.appendChild(photoDiv);
                }
                postLink.appendChild(listItem);
                postListContainer.appendChild(postLink);
            });
        } else {
            postListContainer.innerHTML = '<p style="text-align:center;">게시글이 없습니다.</p>';
        }
    } catch (error) {
        console.error('게시글을 불러오는 중 오류 발생:', error);
        postListContainer.innerHTML = `<p style="text-align:center; color:red;">게시글을 불러오는 데 실패했습니다: ${error.message}</p>`;
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const navLinks = document.querySelectorAll('.board-nav a');
    // const postsContainer = document.querySelector('.content-section'); // 이제 postListContainer 전역 변수 사용
    
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();

            navLinks.forEach(nav => nav.classList.remove('active'));
            this.classList.add('active');

            const selectedFilter = this.textContent.trim();
            sessionStorage.setItem(activeTabStorageKey, selectedFilter);
            
            let apiUrlToFetch; // 새 변수명 사용
            if (selectedFilter === '인기') {
                // apiUrl = `${boardApiUrl}?sortBy=popularity&order=desc`; // 서버 지원 가정
                apiUrlToFetch = popularArticlesUrl;
            } else if (selectedFilter === '공지') {
                apiUrlToFetch = noticeArticlesUrl;
            } else if (selectedFilter === '자유') {
                apiUrlToFetch = freeArticlesUrl;
            } else if (selectedFilter === '정보') {
                apiUrlToFetch = infoArticlesUrl;
            } else if (selectedFilter === '최신') {
                // apiUrl = boardApiUrl; // 기본 URL (최신순으로 가정)
                apiUrlToFetch = recentArticlesUrl;
            } else {
                // 혹시 모를 다른 탭이나 기본값 처리
                console.warn(`알 수 없는 필터: ${selectedFilter}, 최신 글로 대체합니다.`);
                apiUrlToFetch = recentArticlesUrl; 
            }
            // 다른 필터(예: 제목 검색 등)가 있다면 여기에 추가 로직

            fetchAndDisplayPosts(apiUrlToFetch);
        });
    });

    const storedTab = sessionStorage.getItem(activeTabStorageKey);
    let tabToActivate = null;

    if (storedTab) {
        tabToActivate = Array.from(navLinks).find(link => link.textContent.trim() === storedTab);
    }

    if (tabToActivate) {
        tabToActivate.click();
    } else {
        const latestNav = Array.from(navLinks).find(link => link.textContent.trim() === '최신');
        if (latestNav) {
            latestNav.click(); 
        } else if (navLinks.length > 0) {
            navLinks[0].click(); // 최신 탭이 없으면 첫번째 탭이라도 활성화
        }
    }
}); 
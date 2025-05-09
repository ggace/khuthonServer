// auth-modal.js
document.addEventListener('DOMContentLoaded', () => {
    const authModal = document.getElementById('auth-modal');
    const openModalButton = document.getElementById('open-auth-modal-button');
    const closeModalButton = document.querySelector('.close-auth-modal-button');
    const modalOverlay = document.querySelector('.modal-overlay');

    const loginFormContainer = document.getElementById('login-form-container');
    const signupFormContainer = document.getElementById('signup-form-container');
    const showSignupLink = document.getElementById('show-signup-link');
    const showLoginLink = document.getElementById('show-login-link');

    const baseApiUrl = 'http://172.21.12.44:5500/api/user';

    // 로그인 상태 확인 및 UI 업데이트 함수
    async function checkLoginStatus() {

        fetch("http://172.21.12.44:5500/api/user/islogined")
        .then((data) => data.text())
        .then((data) => {
            {
                if (openModalButton) {
                    if(data === 'true') {
                        openModalButton.textContent = '로그아웃';
                        openModalButton.removeEventListener('click', openModal); 
                        openModalButton.addEventListener('click', handleLogout);
                    } else {
                        openModalButton.textContent = '로그인 / 회원가입';
                        openModalButton.removeEventListener('click', handleLogout); 
                        openModalButton.addEventListener('click', openModal);
                    }
                }                
            }
        })

        const loggedInUser = sessionStorage.getItem('loggedInUser'); // 예시: loginId 저장
        
    }

    // 로그아웃 처리 함수
    async function handleLogout() {
        try {
            const response = await fetch(`${baseApiUrl}/logout`, {
                method: 'GET', // 명시적으로 GET 설정 (fetch 기본값이긴 함)
                // 필요한 경우 headers: { 'Authorization': 'Bearer ' + token } 등 추가
            });
            // 서버 응답 상태와 관계없이 클라이언트 측 로그아웃 처리
            if (!response.ok) {
                console.warn('로그아웃 API 호출 실패:', response.status, await response.text().catch(() => ''));
            }
        } catch (error) {
            console.error('로그아웃 API 요청 중 오류:', error);
        } finally {
            sessionStorage.removeItem('loggedInUser');
            // sessionStorage.removeItem('authToken'); // 토큰 사용 시 토큰도 제거
            alert('로그아웃 되었습니다.');
            window.location.reload();
        }
    }

    function openModal() {
        if (authModal) authModal.style.display = 'flex';
    }

    function closeModal() {
        if (authModal) authModal.style.display = 'none';
    }

    checkLoginStatus();

    if (openModalButton && !sessionStorage.getItem('loggedInUser')) { // 로그아웃 상태일 때만 모달 열기 리스너
        openModalButton.addEventListener('click', openModal);
    }

    if (closeModalButton) {
        closeModalButton.addEventListener('click', closeModal);
    }

    if (modalOverlay) {
        modalOverlay.addEventListener('click', closeModal); 
    }

    window.addEventListener('keydown', (event) => {
        if (event.key === 'Escape' && authModal && authModal.style.display === 'flex') {
            closeModal();
        }
    });

    if (showSignupLink) {
        showSignupLink.addEventListener('click', (e) => {
            e.preventDefault();
            if (loginFormContainer) loginFormContainer.style.display = 'none';
            if (signupFormContainer) signupFormContainer.style.display = 'block';
        });
    }

    if (showLoginLink) {
        showLoginLink.addEventListener('click', (e) => {
            e.preventDefault();
            if (signupFormContainer) signupFormContainer.style.display = 'none';
            if (loginFormContainer) loginFormContainer.style.display = 'block';
        });
    }

    const loginForm = document.getElementById('login-form');
    // if (loginForm) {
    //     loginForm.addEventListener('submit', async (e) => {
    //         e.preventDefault();
    //         const loginId = loginForm.querySelector('#login-email').value;
    //         const loginPw = loginForm.querySelector('#login-password').value;

    //         if (!loginId || !loginPw) {
    //             alert('이메일과 비밀번호를 모두 입력해주세요.');
    //             return;
    //         }

    //         try {
    //             console.log(`${baseApiUrl}/login`);
    //             let data = { loginId: loginId, loginPw:loginPw };
    //             const response = await fetch(`${baseApiUrl}/login`, {
    //                 method: 'Get',
    //                 body: JSON.stringify()
    //             });

    //             if (!response.ok) {
    //                 const errorData = await response.json().catch(() => ({ message: '로그인에 실패했습니다. 다시 시도해주세요.'}));
    //                 throw new Error(errorData.message || `로그인 실패: ${response.status}`);
    //             }
                
    //             // 로그인 성공 시, 서버가 사용자 정보나 토큰을 반환한다면 여기서 처리
    //             // const userData = await response.json(); 
    //             // sessionStorage.setItem('authToken', userData.token); // 예시: 토큰 저장
    //             sessionStorage.setItem('loggedInUser', loginId); // 예시: 사용자 아이디 저장

    //             alert('로그인 되었습니다!');
    //             closeModal(); 
    //             checkLoginStatus(); // UI 업데이트
    //         } catch (error) {
    //             console.error('로그인 API 오류:', error);
    //             alert(error.message);
    //         }
    //     });
    // }

    const signupForm = document.getElementById('signup-form');
    if (signupForm) {
        signupForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const nickname = signupForm.querySelector('#signup-name').value; // API: nickname
            const loginId = signupForm.querySelector('#signup-email').value;  // API: loginId
            const loginPw = signupForm.querySelector('#signup-password').value; // API: loginPw
            const passwordConfirm = signupForm.querySelector('#signup-password-confirm').value;

            if (!nickname || !loginId || !loginPw || !passwordConfirm) {
                alert('모든 필드를 입력해주세요.');
                return;
            }
            if (loginPw !== passwordConfirm) {
                alert('비밀번호가 일치하지 않습니다.');
                return;
            }

            
        });
    }
}); 
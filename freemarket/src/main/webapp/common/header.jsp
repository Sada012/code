<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="bean.User"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>FeeMa</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <%
    User user = (User) session.getAttribute("user");
    int authority = 0;
    if (user != null) {
        authority = user.getAuthority();
    }
    %>
    <header id="<%= (authority == 1) ? "admin" : (authority == 2) ? "user" : "guest" %>">
        <div class="header-container">
            <div class="app-name">
                <h1><a href="<%=request.getContextPath()%>/goodsList">
                    <img src="<%=request.getContextPath()%>/img/common/logo.png" alt="ロゴ" class="logo">
                    </a>
                </h1>
            </div>
            <div class="user-info">
                <% if (user != null) { %>
                    <div style="margin-right: 5%">
                        <% if (authority == 1) { %>
                            <a>admin</a>
                        <% } else if (authority == 2) { %>
                            <a><%= user.getNickname() %></a>
                        <% } %>
                    </div>
                    <img src="<%=request.getContextPath()%><%= user.getIcon() %>" alt="ユーザーアイコン" class="user-icon">
                    <nav class="menu">
                        <ul>
                            <% if (authority == 1) { %>
                                <li><a href="<%=request.getContextPath()%>/view/adminMenu.jsp">マイページ</a></li>
                            <% } else if (authority == 2) { %>
                                <li><a href="<%=request.getContextPath()%>/view/mypage.jsp">マイページ</a></li>
                            <% } %>
                            <li><a href="<%=request.getContextPath()%>/logout">ログアウト</a></li>
                            <li><button id="dark-mode-toggle">ダークモード</button></li>
                        </ul>
                    </nav>
                <% } else { %>
                    <nav class="menu">
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/view/login.jsp">ログイン</a></li>
                            <li><a href="<%=request.getContextPath()%>/view/signUp.jsp">新規登録</a></li>
                            <li><button id="dark-mode-toggle">ダークモード</button></li>
                        </ul>
                    </nav>
                <% } %>
            </div>
        </div>
    </header>

    <!-- コンテンツはここに -->

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const darkModeToggle = document.getElementById('dark-mode-toggle');
            if (darkModeToggle) {
                darkModeToggle.addEventListener('click', () => {
                    document.body.classList.toggle('dark-mode');
                });
            }
        });
    </script>
</body>
</html>

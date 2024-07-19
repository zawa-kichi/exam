<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
    <title>メニュー</title>
    <style>
        .menu-container {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            align-items: stretch; /* 各項目の高さを揃える */
            padding: 20px 0;
        }
        .menu-group {
            flex: 1;
            text-align: center;
            border-radius: 15px;
            padding: 10px;
            margin: 0 10px; /* 項目間のスペースを調整 */
        }
        .student-management {
            background-color: #ffb6c1; /*ライトピンク*/
        }
        .grade-management {
            background-color: #7fffd4; /*アクアマリン*/
        }
        .subject-management {
            background-color: #f0e68c; /*カーキ*/
        }
        .menu-group ul {
            list-style-type: none;
            padding: 0;
        }
        .menu-group ul li {
            margin: 5px 0;
        }
    </style>
  <!-- カラーの参考は https://www.colordic.org/ から引用 -->
</head>
<body>

<form action="Menu.action">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <div class="container">
        <h2 class="text-center">メニュー</h2>
        <div class="menu-container">
            <div class="menu-group student-management">
                <h3>学生管理</h3>
                <ul>
                    <li>
                    <a href="StudentList.action">学生管理</a>
                    </li>

                </ul>
            </div>
            <div class="menu-group grade-management">
                <h3>成績管理</h3>
                <ul>
                    <li><a href="TestRegist.action">成績登録</a></li>
                    <li><a href="TestList.action">成績参照</a></li>
                </ul>
            </div>
            <div class="menu-group subject-management">
                <h3>科目管理</h3>
                <ul>
                    <li><a href="SubjectList.action">科目管理</a></li>
                </ul>
            </div>
        </div>
    </div>
    <footer class="mt-4 text-center">
        <p>©2023 THC</p>
        <p>大原学園</p>
    </footer>
    </form>
</body>
</html>

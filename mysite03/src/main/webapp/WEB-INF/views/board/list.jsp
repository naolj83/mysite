<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
						<form id="search_form" action="${pageContext.request.contextPath }/board" method="post">
							<input type="text" id="kwd" name="kwd" value="">
							<input type="submit" value="찾기">
						</form>
						<ul>
					<li>
						<table class="tbl-ex">
							<tr>
								<th>번호</th>
								<th style="text-align: left">제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>&nbsp;</th>
							</tr>
							<c:set var="count" value="${fn:length(list) }" />
							<c:forEach items="${list }" var="vo" varStatus="status">

								<tr>
									<td>[${count-status.index }]</td>
									<c:choose>
									<c:when test = "${vo.depth > 0 }">
									<td style="text-align:left; padding-left: $vo.depth * 20 }px"><img src="${pageContext.servletContext.contextPath }/assets/images/reply.png"/>
								<a href="${pageContext.request.contextPath }/board/view/${vo.no }">${vo.title }</a></td>
										</c:when>
										<c:otherwise>
										<td style="text-align:left;"><a
										href="${pageContext.request.contextPath }/board/view/${vo.no }">${vo.title }</a></td></c:otherwise>
										</c:choose>
									<td>${vo.userName }</td>
									<td>${vo.hit }</td>
									<td>${vo.regDate }</td>
									<c:if test="${authUser.no == vo.userNo }">
									<td><a href="${pageContext.request.contextPath }/board/delete/${vo.no}" class="del" style='background-image:url("${pageContext.request.contextPath }/assets/images/recycle.png")'>삭제</a></td>
									</c:if>
								<tr>
							</c:forEach>
						</table> <br>
					</li>

				</ul>

						<!-- pager 추가 -->
						<div class="pager">
							<ul>
								<li><a href="">◀</a></li>
								<li><a href="/mysite02/board?p=1">1</a></li>
								<li class="selected">2</li>
								<li><a href="/mysite02/board?p=3">3</a></li>
								<li>4</li>
								<li>5</li>
								<li><a href="">▶</a></li>
							</ul>
						</div>
						<!-- pager 추가 -->

						<div class="bottom">
						<c:if test = "${not empty authUser }">
							<a href="${pageContext.request.contextPath }/board/write"
								id="new-book">글쓰기</a>
								</c:if>
						</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
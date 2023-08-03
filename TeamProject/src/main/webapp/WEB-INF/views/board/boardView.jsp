<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/boardView.jsp</title>
<script type="text/javascript" src="resources/js/go.js"></script>
</head>
<body>
	<input name="token" value="${token }" type="hidden">
	<c:forEach var="tki" items="${boards }">
		<table id="boardViewTbl" border="1">
			<tr>
				<td id="boardViewTitle" name="tp_b_title">${tki.tp_b_title }</td>
				<td align="center" id="boardViewUD">
					<c:if test="${sessionScope.loginMember.tp_m_nick == tki.tp_b_writer }">
						<button onclick="boardUpdateGo(${tki.tp_b_no }, '${tki.tp_b_writer}');">수정</button>&nbsp;&nbsp;
						<button onclick="boardDeleteGo(${tki.tp_b_no }, '${tki.tp_b_writer}' );">삭제</button>&nbsp;&nbsp;
					</c:if>
					<button onclick="boardGo();">목록</button>
				</td>
			</tr>
			<tr>
				<td id="boardViewWriter">${tki.tp_b_writer }</td>
				<td id="boardViewDate"><fmt:formatDate value="${tki.tp_b_when }"
						pattern="yyyy-MM-dd E HH:mm:ss" /></td>
			</tr>
			<tr>
				<td colspan="2" id="boardViewText"><c:if test="${tki.tp_b_photo != null }">
				<img id="boardViewImg" src="resources/img/${tki.tp_b_photo }" style="max-width: 50%;"><br></c:if>
				${tki.tp_b_txt }
				</td>
			</tr>
		</table>
	</c:forEach>
	<c:forEach var="r" items="${Reply }">
	<table border="1"  id="replyTextTbl">
		<tr>
			<td id="replyTextWriter">
				${r.tp_r_writer }
			</td>
			<td align="right"  id="replyTextDate">
				<fmt:formatDate value="${r.tp_r_date }" pattern="yyyy-MM-dd E HH:mm:ss"/>
			</td>
			<td align="center" id="replyTextBtn">
			<c:if test="${sessionScope.loginMember.tp_m_nick == r.tp_r_writer }">
				<button id="replyUpdateBtn${r.tp_r_no }" class="replyUpdateBtn" onclick="replyUpdate(${r.tp_r_no });">수정</button>
			</c:if>
			<c:if test="${sessionScope.loginMember.tp_m_nick == r.tp_r_writer }">
				<button id="replyDeleteBtn${r.tp_r_no }" class="replyDeleteBtn" onclick="replyDeleteGo(${r.tp_r_no });">삭제</button>
			</c:if>
				<button id="replyUpdateDoneBtn${r.tp_r_no }" onclick="replyUpdateDone(${r.tp_r_no });" class="replyUpdateDoneBtn">작성</button>
				<button id="replyUpdateCancelBtn${r.tp_r_no }" onclick="replyUpdateCancel(${r.tp_r_no });" class="replyUpdateCancelBtn">취소</button>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<span style="color: red;" id="replyEdited${r.tp_r_no }">${r.tp_r_edit }</span>
				<span id="replyText${r.tp_r_no }">${r.tp_r_text }</span>
				<textarea id="replyTextarea${r.tp_r_no }" class="replyTextarea replyarea" readonly="readonly">${r.tp_r_text }</textarea>
				<textarea id="replyHiddenText${r.tp_r_no }" class="replyHiddenText">${r.tp_r_text }</textarea>
			</td>
		</tr>
	</table><p>
	</c:forEach>
	<c:if test="${sessionScope.loginMember ne null and sessionScope.boardManager.tp_b_notice ne 1}">
	<form action="reply.write" method="post">
	<input name="token" value="${token }" type="hidden">
	<table border="1" id="replyWriteTbl">
		<tr>
			<td colspan="2">
				<input id="replyWriteWriter" name="tp_r_writer" value="${sessionScope.loginMember.tp_m_nick }" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td id="replyWriteText" align="center">
				<textarea id="replyWriteTextarea" class="replyarea" name="tp_r_text"></textarea>
			</td>
			<td align="center">
				<button>작성</button>
			</td>
		</tr>
	</table>
	</form>
	</c:if>
</body>
</html>
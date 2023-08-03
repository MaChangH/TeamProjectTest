<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardUpdate.jsp</title>
</head>
<body>
	<table>
		<tr>
			<th colspan="2" align="left">글 수 정</th>
		</tr>
	</table>
	<table border="1" id="boardUpdateTbl">
		<c:forEach var="tki" items="${boards }">
			<tr>
				<td>
					<form action="board.update" method="post" name="writeForm" enctype="multipart/form-data"
						onsubmit="return writeCheck();">
						<input name="token" value="${token }" type="hidden">
						<table id="updateTbl" border="1">
							<tr>
								<td id="boardUpdateTitle">제목 :<input name="tp_b_no"
									value="${tki.tp_b_no }" type="hidden" readonly="readonly"></td>
								<td><input id="boardUpdateTitleContext" name="tp_b_title" autocomplete="off" maxlength="50"
									value="${tki.tp_b_title }"></td>
							</tr>
							<tr>
								<td colspan="2" id="boardUpdateText" align="center">
									<c:if test="${tki.tp_b_photo != null }">
										<img id="boardUpdateImg" src="resources/img/${tki.tp_b_photo }" style="max-width: 50%;">
										<input type="file" name="tp_b_photo"><br>
									</c:if> 
									<textarea class="textarea" id="boardUpdateTextarea" name="tp_b_txt" style="resize: none;" maxlength="300" rows="10">${sessionScope.boardText }</textarea>
								</td>
							</tr>
						</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<button>작성</button>
				</form>
					<button onclick="boardViewGo(${tki.tp_b_no});">취소</button>
					</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
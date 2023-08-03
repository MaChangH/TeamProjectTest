package com.teamproject.teamproject.board;

import java.util.List;

public interface BoardMapper {

	// 게시글 갯수
	public abstract int getAllBoardCount();
	
	// 검색어 해당하는 게시글 갯수
	public abstract int getSearchBoardCount(BoardSelector bSel);
	
	// 검색어 해당하는 게시글
	public abstract List<Board> getAllBoard(BoardSelector bSel);
	
	// 공지사항
	public abstract List<Board> getAllNotice();
	
	// 게시글 클릭시 전체 정보
	public abstract List<Board> viewBoard(BoardNo bn);
	
	// 게시글 작성
	public abstract int writeBoard(Board b);
	
	// 게시글 작성(사진 포함)
	public abstract int writeBoardPhoto(Board b);
	
	// 게시글 삭제
	public abstract int deleteBoard(BoardNo bn);
	
	// 게시글 수정
	public abstract int updateBoard(Board b);
	
	// 게시글 수정(사진 포함)
	public abstract int updateBoardPhoto(Board tk);
	
	// 댓글 보여주기
	public abstract List<Reply> getReply(ReplyNo r);
	
	// 댓글 작성
	public abstract int writeReply(Reply r);
	
	// 댓글 삭제
	public abstract int deleteReply(ReplyNo rn);
	
	public abstract int updateReply(Reply r);
}

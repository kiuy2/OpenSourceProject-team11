package com.entity;

import java.util.ArrayList;

public class PageTO {
	ArrayList<BoardDTO> boardList;
	ArrayList<UserTO> userList;
	int curPage;
	int perPage=8;
	int totalCount;
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public ArrayList<BoardDTO> getBoardList() {
		return boardList;
	}
	public void setBoardList(ArrayList<BoardDTO> boardList) {
		this.boardList = boardList;
	}
	public ArrayList<UserTO> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<UserTO> userList) {
		this.userList = userList;
	}
	
}

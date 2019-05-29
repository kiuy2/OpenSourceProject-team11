package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.entity.BoardDTO;
import com.entity.PageTO;

public class BoardDAO {
	
	DataSource ds;
	
	//생성자
	public BoardDAO(){
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/mysql");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	//게시물 리스트 보여주기
	public ArrayList<BoardDTO> list(){
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			String query = "SELECT num,author,title,content,"
					+ "DATE_FORMAT(writeday,'%Y/%M/%D') writeday,"
					+ "readcnt,repRoot, repStep, repIndent FROM "
					+ "board order by repRoot desc, repStep asc";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num=rs.getInt("num");
				String author =rs.getString("author");
				String title = rs.getString("title");
				String content =rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt=rs.getInt("readcnt");
				int repRoot=rs.getInt("repRoot");
				int repStep=rs.getInt("repStep");
				int repIndent=rs.getInt("repIndent");
				
				BoardDTO data =new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				list.add(data);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//글 쓰기
	public void write(String _title, String _author, String _content) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			String sql = "select ifnull(max(num), 0)+1 from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int currval=1;
			if (rs.next()) currval = rs.getInt(1);

			String query = "INSERT INTO board( title, author,content,"
					+ "repRoot,repStep, repIndent) values (?,?,?,?, 0, 0)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			pstmt.setInt(4, currval);
			int n = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 조회수 1증가
	public void readCount(String _num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			String query = "UPDATE board SET readcnt = readcnt + 1 WHERE num=" + _num;
			
			pstmt = con.prepareStatement(query);
			
			int n = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//게시물 자세히 보기
	public BoardDTO retrieve(String _num) {
		
		//조회수 1증가
		readCount(_num);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO data = new BoardDTO();
		
		try {
			con = ds.getConnection();
			String query = "SELECT * FROM board WHERE num = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				
				data.setNum(num);
				data.setTitle(title);
				data.setAuthor(author);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	//게시물 수정
	public void update(String _num, String _title, String _author, String _content) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String query = "UPDATE board SET title = ?, author = ?,"
					+ "content = ? WHERE num = ?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			pstmt.setInt(4, Integer.parseInt(_num));
			
			int n = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//게시물 삭제
	public void delete(String _num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String query = "DELETE FROM board WHERE num=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			int n = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//답변 글 쓰기 UI 로드
	public BoardDTO replyui(String _num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO data = new BoardDTO();

		try {
			con = ds.getConnection();
			String query = "SELECT * FROM board WHERE num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setTitle(rs.getString("title"));
				data.setAuthor(rs.getString("author"));
				data.setContent(rs.getString("content"));
				data.setWriteday(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setRepRoot(rs.getInt("repRoot"));
				data.setRepStep(rs.getInt("repStep"));
				data.setRepIndent(rs.getInt("repIndent"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	//답변시 repStep 1 증가
	public void makeReply(String _root, String _step) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String query = "UPDATE board SET repStep = repStep + 1 "
					+ "WHERE repRoot = ? AND repStep > ?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, Integer.parseInt(_root));
			pstmt.setInt(2, Integer.parseInt(_step));
			
			int n = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//답변달기
	public void reply(String _num, String _title, String _author, String _content,
			String _repRoot, String _repStep, String _repIndent) {
		
		//repStep + 1
		makeReply(_repRoot, _repStep);
	
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			String query = "INSERT INTO board(title, author, content, repRoot,"
					+ "repStep, repIndent) values (?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			pstmt.setInt(4, Integer.parseInt(_repRoot));
			pstmt.setInt(5, Integer.parseInt(_repStep) + 1);
			pstmt.setInt(6, Integer.parseInt(_repIndent) + 1);
			
			int n = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//글 검색하기
	public ArrayList<BoardDTO> search(String _searchName, String _searchValue){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			String query = "SELECT num, author, title,SELECT num,author,title,content," + 
					"DATE_FORMAT(writeday,'%Y/%M/%D') writeday, readcnt FROM board";
			if( _searchName.equals("title")) {
				query+="WHERE title LIKE ?";
			}
			else {
				query+="WHERE author LIKE ?";
			}
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+_searchValue+"%");
			rs = pstmt.executeQuery();
			while( rs.next()) {
				int num=rs.getInt("num");
				String author =rs.getString("author");
				String title = rs.getString("title");
				String content =rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt=rs.getInt("readcnt");
				
				BoardDTO data =new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				
				list.add(data);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int totalCount() {
		
		int count=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			String query = "SELECT count(*) FROM board";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	//페이지 구현
	public PageTO page(int curPage) {
		PageTO to = new PageTO();
		int totalCount =totalCount();
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			String query = "SELECT num,author,title,content,"
					+ "DATE_FORMAT(writeday,'%Y/%M/%D') writeday,"
					+ "readcnt,repRoot, repStep, repIndent FROM "
					+ "board order by repRoot desc, repStep asc";
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
			int perPage=to.getPerPage();//5
			
			int skip = (curPage -1 ) * perPage;
			
			if( skip>0) {
				rs.absolute(skip);
			}
			for( int i=0; i<perPage && rs.next(); i++) {
				int num=rs.getInt("num");
				String author =rs.getString("author");
				String title = rs.getString("title");
				String content =rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt=rs.getInt("readcnt");
				int repRoot=rs.getInt("repRoot");
				int repStep=rs.getInt("repStep");
				int repIndent=rs.getInt("repIndent");
				
				BoardDTO data =new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				list.add(data);
			}
			to.setList(list);
			to.setTotalCount(totalCount);
			to.setCurPage(curPage);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return to;
	}
}

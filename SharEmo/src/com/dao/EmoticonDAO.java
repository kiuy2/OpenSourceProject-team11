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

import com.entity.EmoticonTO;

public class EmoticonDAO {

	DataSource ds;

	// 생성자
	public EmoticonDAO() {
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<EmoticonTO> getEmoticon() {
		ArrayList<EmoticonTO> ticon = new ArrayList<EmoticonTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			String query = "SELECT boardnum, sysname FROM emoticon;";
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				int num = rs.getInt("boardnum");
				String sysname = rs.getString("sysname");
				EmoticonTO data = new EmoticonTO();
				data.setBoardnum(num);
				data.setSrc(sysname);
				ticon.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ticon;
	}

	// 해당 게시물의 이모티콘만 추출
	public ArrayList<EmoticonTO> getEmoticon(String _num) {
		ArrayList<EmoticonTO> ticon = new ArrayList<EmoticonTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			String query = "SELECT * FROM emoticon where boardnum=" + _num;
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				int num = rs.getInt("boardnum");
				String sysname = rs.getString("sysname");
				String orgname = rs.getString("orgname");
				EmoticonTO data = new EmoticonTO();
				data.setBoardnum(num);
				data.setSrc(sysname);
				data.setOrg(orgname);
				ticon.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ticon;
	}

	public int writeEmoticon(String _sysname, String _orgname, String num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardnum = 0;
		try {
			con = ds.getConnection();
			
			if(num==null) {
				String sql = "select ifnull(max(num), 0) from board";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
		
				if (rs.next())
					boardnum = rs.getInt(1);
			}
			else
				boardnum=Integer.parseInt(num);
	
			String query = "INSERT INTO emoticon( boardnum, sysname, orgname)" + " values (?,?,?)";
			pstmt = con.prepareStatement(query);
	
			pstmt.setInt(1, boardnum);
			pstmt.setString(2, _sysname);
			pstmt.setString(3, _orgname);
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return boardnum;
	}

	public void deleteEmoticon(String num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			String query = "delete from emoticon where boardnum="+num;
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteEmoticon(String num, String sysname) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			String query = "delete from emoticon where boardnum="+num+" and sysname='"+sysname+"'";
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

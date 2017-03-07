package com.bjpowernode.drp.basedata.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bjpowernode.drp.util.Dbutil;

/**
 * ��ɷ��������ĵݹ��ȡ
 * 
 * @author Administrator
 *
 */
public class ClientTreeReader {

	private StringBuffer sbTreeHTML = new StringBuffer();

	/**
	 * ����HTML�ַ���
	 * 
	 * @return
	 */
	public String getClientTreeHTMLString() {
		Connection conn = null;
		try {
			conn = Dbutil.getConnection();
			readClientTree(conn, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Dbutil.close(conn);
		}
		return sbTreeHTML.toString();
	}

	// /**
	// * �ݹ��ȡ��������
	// *
	// * ��һ���� ����򵥵ķ�ʽ��ȡ���������νṹ��������ʾ��������м���
	// * @param conn
	// * @param id
	// * @param level ���Ʋ��
	// */
	// private void readClientTree(Connection conn, int id, int level)
	// throws SQLException {
	// String sql = "select * from t_client where pid=?";
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// try {
	// pstmt = conn.prepareStatement(sql);
	// pstmt.setInt(1, id);
	// rs = pstmt.executeQuery();
	// while (rs.next()) {
	// sbTreeHTML.append(rs.getString("name"))
	// .append("<br>\n");
	// if ("N".equals(rs.getString("is_leaf"))) {
	// readClientTree(conn, rs.getInt("id"), level);
	// }
	// }
	// }finally {
	// DbUtil.close(rs);
	// DbUtil.close(pstmt);
	// }
	// }

	// /**
	// * �ݹ��ȡ��������
	// *
	// * �ڶ����� �����θ�
	// * @param conn
	// * @param id
	// * @param level ���Ʋ��
	// */
	// private void readClientTree(Connection conn, int id, int level)
	// throws SQLException {
	// String sql = "select * from t_client where pid=?";
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// try {
	// pstmt = conn.prepareStatement(sql);
	// pstmt.setInt(1, id);
	// rs = pstmt.executeQuery();
	// while (rs.next()) {
	// for (int i=0; i<level; i++) {
	// sbTreeHTML.append("&nbsp;&nbsp;");
	// }
	// sbTreeHTML.append(rs.getString("name"))
	// .append("<br>\n");
	// if ("N".equals(rs.getString("is_leaf"))) {
	// readClientTree(conn, rs.getInt("id"), level + 1);
	// }
	// }
	// }finally {
	// DbUtil.close(rs);
	// DbUtil.close(pstmt);
	// }
	// }

	// /**
	// * �ݹ��ȡ��������
	// *
	// * �������� Ҷ�ӽڵ�ǰ�����һ����-���ţ���Ҷ�ӽڵ�ǰ�����һ����+����
	// * @param conn
	// * @param id
	// * @param level ���Ʋ��
	// */
	// private void readClientTree(Connection conn, int id, int level)
	// throws SQLException {
	// String sql = "select * from t_client where pid=?";
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// try {
	// pstmt = conn.prepareStatement(sql);
	// pstmt.setInt(1, id);
	// rs = pstmt.executeQuery();
	// while (rs.next()) {
	// for (int i=0; i<level; i++) {
	// sbTreeHTML.append("&nbsp;&nbsp;");
	// }
	// if ("N".equals(rs.getString("is_leaf"))) {
	// sbTreeHTML.append("+")
	// .append(rs.getString("name"))
	// .append("<br>\n");
	// readClientTree(conn, rs.getInt("id"), level + 1);
	// }else {
	// sbTreeHTML.append("-")
	// .append(rs.getString("name"))
	// .append("<br>\n");
	// }
	// }
	// }finally {
	// DbUtil.close(rs);
	// DbUtil.close(pstmt);
	// }
	// }

	/**
	 * �ݹ��ȡ��������
	 * 
	 * ���Ĳ��� ����div�������νṹ
	 * 
	 * @param conn
	 * @param id
	 * @param level
	 *            ���Ʋ��
	 */
	private void readClientTree(Connection conn, int id, int level)
			throws SQLException {
		String sql = "select * from t_client where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sbTreeHTML.append("<div>");
				sbTreeHTML.append("\n");
				for (int i = 0; i < level; i++) {
					sbTreeHTML.append("<img src=\"../images/white.gif\">");
					sbTreeHTML.append("\n");
				}
				// if ("N".equals(rs.getString("is_leaf"))) {
				if ("N".equals(rs.getString("is_leaf"))) {
					sbTreeHTML
							.append("<img alt=\"չ��\" style=\"cursor:hand;\" onClick=\"display('"
									+ rs.getInt("id")
									+ "');\" id=\"img"
									+ rs.getInt("id")
									+ "\" src=\"../images/plus.gif\">");
					sbTreeHTML.append("\n");
					sbTreeHTML.append("<img id=\"im" + rs.getInt("id")
							+ "\" src=\"../images/closedfold.gif\">");
					sbTreeHTML.append("\n");
					sbTreeHTML.append("<a href=\"client_node_crud.jsp?id="
							+ rs.getInt("id")
							+ "\" target=\"clientDispAreaFrame\">"
							+ rs.getString("name") + "</a>");
					sbTreeHTML.append("\n");
					sbTreeHTML.append("<div style=\"display:none;\" id=\"div"
							+ rs.getInt("id") + "\">");
					sbTreeHTML.append("\n");
					readClientTree(conn, rs.getInt("id"), level + 1);
					sbTreeHTML.append("</div>");
					sbTreeHTML.append("\n");
				} else {
					sbTreeHTML.append("<img src=\"../images/minus.gif\">");
					sbTreeHTML.append("\n");
					sbTreeHTML.append("<img src=\"../images/openfold.gif\">");
					sbTreeHTML.append("\n");
					// if ("Y1".equals(rs.getString("is_client"))) {
					if ("Y".equals(rs.getString("is_client"))) {
						sbTreeHTML.append("<a href=\"client_crud.jsp?id="
								+ rs.getInt("id")
								+ "\" target=\"clientDispAreaFrame\">"
								+ rs.getString("name") + "</a>");
					} else {
						sbTreeHTML.append("<a href=\"client_node_crud.jsp?id="
								+ rs.getInt("id")
								+ "\" target=\"clientDispAreaFrame\">"
								+ rs.getString("name") + "</a>");
					}
					sbTreeHTML.append("\n");
				}
				sbTreeHTML.append("</div>");
				sbTreeHTML.append("\n");
			}
		} finally {
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
	}

}
package hu.ingatlankozvetites.dao;

import hu.ingatlankozvetites.beans.Ajanlat;
import hu.ingatlankozvetites.beans.Felh;
import hu.ingatlankozvetites.beans.Idopont;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBControl {
	private static DBConnect instance;
	private static String dbfile;
	
	public DBControl() {		
			try {
				instance = new DBConnect();
				dbfile = instance.getDbfile();
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
			}					
	}
	
	
	private static boolean toBoolean(int i) {
		if (i==1) return true;
		else return false;
	}
	@SuppressWarnings("unused")
	private static int toInt(boolean i) {
		if (i==true) return 1;
		else return 0;
	}
	
	public boolean select(String sql) throws SQLException {
		Connection conn = null;
		Statement st = null;
		boolean res = false;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
						
			if (rs.isBeforeFirst()) res = true;
			return res;		
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public List<Ajanlat> getAjanlat(String sql) throws SQLException {
		List<Ajanlat> osszes = new ArrayList<Ajanlat>();
		Ajanlat tmp = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						
			if (rs.isBeforeFirst())
				while (rs.next()) {
					tmp = new Ajanlat();
					tmp.setAj_id(rs.getInt("aj_id"));
					tmp.setLeiras(rs.getString("leiras"));
					tmp.setAr(rs.getInt("ar"));
					tmp.setCim(rs.getString("cim"));
					tmp.setTerulet(rs.getInt("terulet"));
					tmp.setElado(rs.getInt("elado"));
					tmp.setHaz(rs.getInt("haz"));
					tmp.setUdvar(rs.getInt("udvar")); 
					tmp.setKert(rs.getInt("kert"));
					tmp.setTerasz(rs.getInt("terasz"));
					tmp.setErkely(rs.getInt("erkely")); 
					tmp.setMedence(rs.getInt("medence")); 
					tmp.setGarazs(rs.getInt("garazs"));
					
					try {
						java.util.Date utilDate = df.parse(rs.getString("idop"));
						
						tmp.setIdop(
								new java.sql.Date(
										utilDate.getTime()
								));
					} catch(Exception e) {
						e.printStackTrace();
					}
					
					tmp.setHirdeto_nev(rs.getString("hirdeto_nev"));
					osszes.add(tmp);
				}
			return osszes;		
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Felh> getUser(String sql) throws SQLException {
		List<Felh> osszes = new ArrayList<Felh>();
		Felh tmp = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
						
			if (rs.isBeforeFirst())
				while (rs.next()) {
					tmp = new Felh();
					tmp.setFelh_nev(rs.getString("felh_nev"));
					tmp.setJelszo(rs.getString("jelszo"));
					tmp.setNev(rs.getString("nev"));
					tmp.setEmail(rs.getString("email"));
					tmp.setTel_sz(rs.getString("tel_sz"));
					tmp.setAdmin(toBoolean(rs.getInt("admin")));
					osszes.add(tmp);
				}
			return osszes;		
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Idopont> getIdopont(String sql) throws SQLException {
		List<Idopont> osszes = new ArrayList<Idopont>();
		Idopont tmp = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
						
			if (rs.isBeforeFirst())
				while (rs.next()) {
					int aj_id = rs.getInt("aj_id");
					String erdeklodo = rs.getString("erdeklodo");
					Date idop = rs.getDate("idop");
					String ugynok = rs.getString("ugynok");
					boolean szabad = toBoolean(rs.getInt("szabad"));
					
					tmp = new Idopont(aj_id,idop,szabad,ugynok,erdeklodo);
					
					osszes.add(tmp);
				}
			return osszes;		
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(String sql) throws SQLException {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			st = conn.createStatement();
			st.executeQuery(sql);
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(String sql) throws SQLException {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			st = conn.createStatement();
			st.executeUpdate(sql);
			
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void insert(String sql) throws SQLException {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			st = conn.createStatement();
			st.executeUpdate(sql);
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	public boolean log(String s) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
		
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			pst = conn.prepareStatement("insert into AnimalLog (animal, stamp) values (?, ?)");
			pst.setString(1, s);
			pst.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
			pst.executeUpdate();
		} finally {
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	*/
}

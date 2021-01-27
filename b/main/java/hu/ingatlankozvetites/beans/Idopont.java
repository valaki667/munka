package hu.ingatlankozvetites.beans;

import hu.ingatlankozvetites.dao.DBControl;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Idopont {
	int aj_id;
	Date idop;
	boolean szabad;
	String ugynok;
	String erdeklodo;
	Date vege;
	public Idopont(){}
	
	public int getAj_id() {
		return aj_id;
	}

	public void setAj_id(int aj_id) {
		this.aj_id = aj_id;
	}

	public Date getIdop() {
		return idop;
	}

	public void setIdop(Date idop) {
		this.idop = idop;
	}

	public boolean isSzabad() {
		return szabad;
	}

	public void setSzabad(boolean szabad) {
		this.szabad = szabad;
	}

	public String getUgynok() {
		return ugynok;
	}

	public void setUgynok(String ugynok) {
		this.ugynok = ugynok;
	}

	public String getErdeklodo() {
		return erdeklodo;
	}

	public void setErdeklodo(String erdeklodo) {
		this.erdeklodo = erdeklodo;
	}

	public Date getVege() {
		if (vege == null) {
			this.vege = new Date();
			this.vege.setTime(idop.getTime() + 1800000);			
		}
		return vege;
	}

	public void setVege(Date vege) {
		this.vege = vege;
	}

	
	public Idopont(int aj_id, Date idop, boolean szabad, String ugynok, String felh_nev) {
		this.aj_id = aj_id;
		this.idop = idop;
		this.szabad = szabad;
		this.erdeklodo = felh_nev;
		this.ugynok = ugynok;
		this.vege = new Date();
		this.vege.setTime(idop.getTime() + 1800000);
	}
	public Idopont(int aj_id, Date idop) {
		
		this.aj_id = aj_id;
		this.idop = idop;
		long v = idop.getTime() + 1800000;
		this.vege = new Date();
		this.vege.setTime(v);
		v = 0;
		//this.vege.setTime(idop.getTime() + 1800000);
	}

	public static boolean foglal(String felh_nev, int aj_id, Date idop) throws SQLException {
		DBControl control = new DBControl();		
		Ajanlat ajanlat = control.getAjanlat("select * from Ajanlat WHERE aj_id='"+aj_id+"'").get(0);
		Idopont idopont = new Idopont(aj_id, idop, false, ajanlat.getHirdeto_nev(), felh_nev);
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String ido = formatter.format(idopont.getIdop());
		if (ellenorzes(aj_id,idop)) {
			control.insert("INSERT INTO Idopont ('aj_id','idop','erdeklodo','ugynok') VALUES ('"+idopont.getAj_id()+"', "
														+ "'"+ido+"', "
														+ "'"+idopont.getErdeklodo()+"', "
														+ "'"+idopont.getUgynok()+"' "
														+ ")");
			return true;
		}		
		return false;
	}
	public static Idopont foglal(Idopont idopont) throws SQLException {
		Idopont foglalt = null;
		DBControl control = new DBControl();	
		if (ellenorzes(idopont.getAj_id(),idopont.getIdop())) {
			foglalt = idopont;
			control.insert("INSERT INTO Idopont ('aj_id','idop','erdeklodo','ugynok') VALUES ('"+idopont.getAj_id()+"', "
														+ "'"+idopont.getIdop()+"', "
														+ "'"+idopont.getErdeklodo()+"', "
														+ "'"+idopont.getUgynok()+"' "
														+ ")");
		}
		return foglalt;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean ellenorzes(int aj_id,Date idop) throws SQLException {
		Idopont ido = new Idopont(aj_id,idop);
		DBControl control = new DBControl();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String kezdet = formatter.format(idop);
		String veg = formatter.format(ido.vege);
		//String kezdet = idop.getYear()+1900+"-"+ ((idop.getMonth()+1)<10) ? "0"+idop.getMonth()+1:idop.getMonth()+1+"-"+idop.getDate()+" "+idop.getHours()+":"+idop.getMinutes();
		//String veg = ido.vege.getYear()+1900+"-"+ido.vege.getMonth()+"-"+ido.vege.getDate()+" "+ido.vege.getHours()+":"+ido.vege.getMinutes();
		String sql = "select * from Idopont WHERE aj_id='"+aj_id+"' AND idop BETWEEN '"+kezdet+"' AND '"+veg+"'";
		if (control.getIdopont(sql).size() > 0) return false;
		return true;
	}
	public static boolean ellenorzes(Idopont ido) throws SQLException {
		return ellenorzes(ido.getAj_id(),ido.getIdop());
	}
	
	@SuppressWarnings("deprecation")
	public static void torol(int aj_id, Date idop) throws SQLException {
		Idopont ido = new Idopont(aj_id, idop);
		String kezdet = idop.getYear()+"-"+idop.getMonth()+"-"+idop.getDate()+" "+idop.getHours()+":"+idop.getMinutes();
		String veg = ido.getVege().getYear()+"-"+ido.getVege().getMonth()+"-"+ido.getVege().getDate()+" "+ido.getVege().getHours()+":"+ido.getVege().getMinutes();
		String sql = "DELETE FROM Idopont WHERE aj_id='"+aj_id+"' AND idop BETWEEN '"+kezdet+"' AND '"+veg+"'";
		
		DBControl control = new DBControl();
		control.delete(sql);
	}
	public static void torol(Idopont t) throws SQLException {
		torol(t.getAj_id(),t.getIdop());
	}
	
	public static List<Idopont> getIdopont(Idopont szur) throws SQLException {
		DBControl control = new DBControl();
		String szures = "";
		List<Idopont> osszes = null;
		String sql = "SELECT * FROM Idopont WHERE ";
		
		if (szur.getAj_id() > 0) szures += "aj_id = '"+szur.getAj_id()+"' AND ";
		if (!szur.ugynok.isEmpty()) szures += "ugynok LIKE '%"+szur.ugynok+"%' AND ";
		if (!szur.erdeklodo.isEmpty()) szures += "erdeklodo LIKE '%"+szur.erdeklodo+"%' AND ";
		/*if (szabad) szures += "szabad = '1' AND ";
		else szures += "szabad = '0' AND ";*/
		
		if (szur.idop != null) szures += "idop BETWEEN '"+szur.idop+"' AND '"+szur.getVege()+"' AND ";
		szures=szures.substring(0, szures.length()-4);
		
		sql+=szures;
		osszes = control.getIdopont(sql);
		return osszes;
	}
	/**
	 * 
	 * @param aj_id ha nem ez alapján szűrünk akkor 0
	 * @param idop 
	 * @param ugynok üres string esetén nem szűr rá
	 * @param erdeklodo üres string esetén nem szűr rá
	 * @return
	 * @throws SQLException
	 */
	public static List<Idopont> getIdopont(int aj_id, Date idop, String ugynok, String erdeklodo) throws SQLException {
		Idopont tmp = new Idopont(aj_id,idop,false,ugynok,erdeklodo);
		return getIdopont(tmp);
	}
	
}

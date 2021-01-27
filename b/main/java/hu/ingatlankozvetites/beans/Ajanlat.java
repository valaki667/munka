package hu.ingatlankozvetites.beans;

import hu.ingatlankozvetites.dao.DBControl;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Ajanlat implements Serializable{
	int aj_id;
	String leiras;
	int ar;
	String cim;
	int terulet;
	int elado;
	int haz;
	int udvar;
	int kert;
	int terasz;
	int erkely;
	int medence;
	int garazs;
	Date idop;
	String hirdeto_nev;
	
	
	public int getAj_id() {
		return aj_id;
	}


	public void setAj_id(int aj_id) {
		this.aj_id = aj_id;
	}


	public String getLeiras() {
		return leiras;
	}


	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}


	public int getAr() {
		return ar;
	}


	public void setAr(int ar) {
		this.ar = ar;
	}


	public String getCim() {
		return cim;
	}


	public void setCim(String cim) {
		this.cim = cim;
	}


	public int getTerulet() {
		return terulet;
	}


	public void setTerulet(int terulet) {
		this.terulet = terulet;
	}


	public int getElado() {
		return elado;
	}


	public void setElado(int elado) {
		this.elado = elado;
	}


	public int getHaz() {
		return haz;
	}


	public void setHaz(int haz) {
		this.haz = haz;
	}


	public int getUdvar() {
		return udvar;
	}


	public void setUdvar(int udvar) {
		this.udvar = udvar;
	}


	public int getKert() {
		return kert;
	}


	public void setKert(int kert) {
		this.kert = kert;
	}


	public int getTerasz() {
		return terasz;
	}


	public void setTerasz(int terasz) {
		this.terasz = terasz;
	}


	public int getErkely() {
		return erkely;
	}


	public void setErkely(int erkely) {
		this.erkely = erkely;
	}


	public int getMedence() {
		return medence;
	}


	public void setMedence(int medence) {
		this.medence = medence;
	}


	public int getGarazs() {
		return garazs;
	}


	public void setGarazs(int garazs) {
		this.garazs = garazs;
	}


	public Date getIdop() {
		return idop;
	}


	public void setIdop(Date idop) {
		this.idop = idop;
	}


	public String getHirdeto_nev() {
		return hirdeto_nev;
	}


	public void setHirdeto_nev(String hirdeto_nev) {
		this.hirdeto_nev = hirdeto_nev;
	}

	// ------------------------------------------------------------------------------------------------

	public Ajanlat(){
		aj_id = 0;
		leiras = "";
		ar = 0;
		cim = "";
		terulet = 0;
		elado = 2;
		haz = 2;
		udvar = 2;
		kert = 2;
		terasz = 2;
		erkely = 2;
		medence = 2;
		garazs = 2;
		idop = new Date(0);
		hirdeto_nev = "";
	}
	

	public Ajanlat(String leiras,int ar, String cim, int terulet, boolean elado, boolean haz,
			boolean udvar, boolean kert, boolean terasz, boolean erkely,
			boolean medence, boolean garazs, Date idop, String hirdeto_nev) {
		super();
		this.aj_id = (int) Math.random();
		this.leiras = leiras;
		this.ar = ar;
		this.cim = cim;
		this.terulet = terulet;
		this.elado = toInt(elado);
		this.haz = toInt(haz);
		this.udvar = toInt(udvar);
		this.kert = toInt(kert);
		this.terasz = toInt(terasz);
		this.erkely = toInt(erkely);
		this.medence = toInt(medence);
		this.garazs = toInt(garazs);
		this.idop = idop;
		this.hirdeto_nev = hirdeto_nev;
	}
	public Ajanlat(int aj_id, String leiras, int ar, String cim, int terulet, boolean elado,
			boolean haz, boolean udvar, boolean kert, boolean terasz,
			boolean erkely, boolean medence, boolean garazs, Date idop,
			String hirdeto_nev) {
		super();
		this.aj_id = aj_id;
		this.leiras = leiras;
		this.ar = ar;
		this.cim = cim;
		this.terulet = terulet;
		this.elado = toInt(elado);
		this.haz = toInt(haz);
		this.udvar = toInt(udvar);
		this.kert = toInt(kert);
		this.terasz = toInt(terasz);
		this.erkely = toInt(erkely);
		this.medence = toInt(medence);
		this.garazs = toInt(garazs);
		this.idop = idop;
		this.hirdeto_nev = hirdeto_nev;
	}
	public Ajanlat(Ajanlat cpy) {
		super();
		this.aj_id = (int) Math.random();
		this.leiras = cpy.getLeiras();
		this.ar = cpy.getAr();
		this.cim = cpy.getCim();
		this.terulet = cpy.getTerulet();
		this.elado = cpy.getElado();
		this.haz = cpy.getHaz();
		this.udvar = cpy.getUdvar();
		this.kert = cpy.getKert();
		this.terasz = cpy.getTerasz();
		this.erkely = cpy.getErkely();
		this.medence = cpy.getMedence();
		this.garazs = cpy.getGarazs();
		this.idop = cpy.getIdop();
		this.hirdeto_nev = cpy.getHirdeto_nev();
	}
	
	public static boolean toBoolean(int i) {
		if (i==1) return true;
		else return false;
	}
	public static int toInt(boolean i) {
		if (i==true) return 1;
		else return 0;
	}
	public static List<Ajanlat> osszesListaz(){		
		DBControl control = new DBControl();
		List<Ajanlat> osszes = null;
		String sql = "SELECT * FROM Ajanlat";
		try {
			osszes = control.getAjanlat(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return osszes;
	}
	/*
	public static List<Ajanlat> szur(String leiras, int ar, String cim, int terulet, boolean elado, boolean haz,
			boolean udvar, boolean kert, boolean terasz,
			boolean erkely, boolean medence, boolean garazs, Date idop,
			String hirdeto_nev) throws SQLException {
		DBControl control = new DBControl();
		String szures = "";
		List<Ajanlat> osszes = null;
		String sql = "SELECT * FROM Ajanlat WHERE ";
		
		if (!leiras.isEmpty()) szures += "leiras LIKE '%"+cim+"%' AND ";
		if (ar > 0) szures += "ar = '"+ar+"' AND ";
		if (!cim.isEmpty()) szures += "cim LIKE '%"+cim+"%' AND ";
		if (terulet > 0) szures += "terulet = "+terulet+" AND ";
		if (elado) szures += "elado = 1 AND "; else if (elado == false) szures += "elado = 0 AND ";
		if (haz) szures += "haz = 1 AND "; else if (haz == false) szures += "haz = 0 AND ";
		if (udvar) szures += "udvar = 1 AND "; else if (udvar == false) szures += "udvar = 0 AND ";
		if (kert) szures += "kert = 1 AND "; else if (kert == false) szures += "kert = 0 AND ";
		if (terasz) szures += "terasz = 1 AND "; else if (terasz == false) szures += "terasz = 0 AND ";
		if (erkely) szures += "erkely = 1 AND "; else if (erkely == false) szures += "erkely = 0 AND ";
		if (medence) szures += "medence = 1 AND "; else if (medence == false) szures += "medence = 0 AND ";
		if (garazs) szures += "garazs = 1 AND "; else if (garazs == false) szures += "garazs = 0 AND ";
		if (idop != null) szures += "idop >= '"+idop+"' AND ";
		if (!hirdeto_nev.isEmpty()) szures += "hirdeto_nev = '"+hirdeto_nev+"' AND ";
		szures=szures.substring(0, szures.length()-4);
		
		
		sql+=szures;
		osszes = control.getAjanlat(sql);
		return osszes;
	}*/
	
	/*
	 * 0 - false, 1 - true, 2 - nincs szures
	 * */
	public static List<Ajanlat> szur(String leiras, int ar, String cim, int terulet, int elado, int haz,
			int udvar, int kert, int terasz,
			int erkely, int medence, int garazs, Date idop,
			String hirdeto_nev) throws SQLException {
		DBControl control = new DBControl();
		String szures = "";
		List<Ajanlat> osszes = null;
		String sql = "SELECT * FROM Ajanlat WHERE ";
		
		if (!leiras.isEmpty()) szures += "leiras LIKE '%"+leiras+"%' AND ";
		if (ar > 0) szures += "ar = '"+ar+"' AND ";
		if (!cim.isEmpty()) szures += "cim LIKE '%"+cim+"%' AND ";
		if (terulet > 0) szures += "terulet = "+terulet+" AND ";
		if (elado==1) szures += "elado = 1 AND "; else if (elado == 0) szures += "elado = 0 AND ";
		if (haz==1) szures += "haz = 1 AND "; else if (haz == 0) szures += "haz = 0 AND ";
		if (udvar==1) szures += "udvar = 1 AND "; else if (udvar == 0) szures += "udvar = 0 AND ";
		if (kert==1) szures += "kert = 1 AND "; else if (kert == 0) szures += "kert = 0 AND ";
		if (terasz==1) szures += "terasz = 1 AND "; else if (terasz == 0) szures += "terasz = 0 AND ";
		if (erkely==1) szures += "erkely = 1 AND "; else if (erkely == 0) szures += "erkely = 0 AND ";
		if (medence==1) szures += "medence = 1 AND "; else if (medence == 0) szures += "medence = 0 AND ";
		if (garazs==1) szures += "garazs = 1 AND "; else if (garazs == 0) szures += "garazs = 0 AND ";
		if (idop != null) szures += "idop >= '"+idop+"' AND ";
		if (!hirdeto_nev.isEmpty()) szures += "hirdeto_nev = '"+hirdeto_nev+"' AND ";
		szures=szures.substring(0, szures.length()-4);
		
		sql+=szures;
		osszes = control.getAjanlat(sql);
		return osszes;
	}
	public static List<Ajanlat> szur(int id) throws SQLException {
		DBControl control = new DBControl();
		List<Ajanlat> osszes = null;
		String sql = "SELECT * FROM Ajanlat WHERE aj_id =" + id;
		
		
		osszes = control.getAjanlat(sql);
		return osszes;
	}
	public static List<Ajanlat> szur(Ajanlat szuro) throws SQLException {		
		return szur(szuro.getLeiras(),szuro.getAr(),szuro.getCim(),szuro.getTerulet(),szuro.getElado(),szuro.getHaz(),
			szuro.getUdvar(),szuro.getKert(),szuro.getTerasz(),
			szuro.getErkely(),szuro.getMedence(),szuro.getGarazs(),szuro.getIdop(),
			szuro.getHirdeto_nev());
	}
	
	public static void felt(String leiras, int ar, String cim, int terulet, boolean elado, boolean haz,
			boolean udvar, boolean kert, boolean terasz,
			boolean erkely, boolean medence, boolean garazs, Date idop,
			String hirdeto_nev) throws SQLException {
		Ajanlat tmp = new Ajanlat(leiras,ar,cim,terulet,elado,haz,udvar,kert,terasz,erkely,medence,garazs,idop,hirdeto_nev);
		DBControl control = new DBControl();
		String sql = "INSERT INTO Ajanlat ('leiras','elado','ar','cim','haz','terulet','udvar','kert','terasz','erkely','medence','garazs','idop','hirdeto_nev') "
				+"VALUES ('"+leiras+"' ,'"+tmp.elado+"',"
				+ "'"+tmp.ar+"'," + "'"+tmp.cim+"'," + "'"+tmp.haz+"'," + "'"+tmp.terulet+"'," 
				+ "'"+tmp.udvar+"'," + "'"+tmp.kert+"'," + "'"+tmp.terasz+"'," 
				+ "'"+tmp.erkely+"'," + "'"+tmp.medence+"'," + "'"+tmp.garazs+"',"
				+ "'"+tmp.idop+"'," + "'"+tmp.hirdeto_nev+"'" + ")";
		try {
			control.insert(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	public static void felt(String leiras2, int ar2, String cim2, int terulet2,
			int elado2, int haz2, int udvar2, int kert2, int terasz2,
			int erkely2, int medence2, int garazs2, java.sql.Date idop2,
			String hirdeto_nev2) throws SQLException {
		felt(leiras2,ar2,cim2,terulet2,toBoolean(elado2),toBoolean(haz2),toBoolean(udvar2),toBoolean(kert2),toBoolean(terasz2),toBoolean(erkely2),toBoolean(medence2),toBoolean(garazs2),idop2,hirdeto_nev2);		
	}
	public static void felt(Ajanlat fel) throws SQLException {
		felt(fel.getLeiras(),fel.getAr(),fel.getCim(),fel.getTerulet(),fel.getElado(),fel.getHaz(),
				fel.getUdvar(),fel.getKert(),fel.getTerasz(),
				fel.getErkely(),fel.getMedence(),fel.getGarazs(),fel.getIdop(),
				fel.getHirdeto_nev());		
	}


	public static void modos(int aj_id, String leiras, int ar, String cim, int terulet, int elado, int haz,
			int udvar, int kert, int terasz,
			int erkely, int medence, int garazs, Date idop,
			String hirdeto_nev) throws SQLException {
		DBControl control = new DBControl();
		String modos = "  ";
		String sql = "UPDATE Ajanlat SET ";
		
		if (ar > 0) modos += "ar = '"+ar+"', ";
		if (!leiras.isEmpty()) modos += "leiras = '"+leiras+"', ";
		if (!cim.isEmpty()) modos += "cim = '"+cim+"', ";
		if (terulet > 0) modos += "terulet = "+terulet+", ";
		if (elado==1) modos += "elado = 1, "; else if (elado == 0) modos += "elado = 0, ";
		if (haz==1) modos += "haz = 1, "; else if (haz == 0) modos += "haz = 0, ";
		if (udvar==1) modos += "udvar = 1, "; else if (udvar == 0) modos += "udvar = 0, ";
		if (kert==1) modos += "kert = 1, "; else if (kert == 0) modos += "kert = 0, ";
		if (terasz==1) modos += "terasz = 1, "; else if (terasz == 0) modos += "terasz = 0, ";
		if (erkely==1) modos += "erkely = 1, "; else if (erkely == 0) modos += "erkely = 0, ";
		if (medence==1) modos += "medence = 1, "; else if (medence == 0) modos += "medence = 0, ";
		if (garazs==1) modos += "garazs = 1, "; else if (garazs == 0) modos += "garazs = 0, ";
		if (idop != null) modos += "idop >= '"+idop+"', ";
		if (!hirdeto_nev.isEmpty()) modos += "hirdeto_nev = '"+hirdeto_nev+"', ";
		modos=modos.substring(0, modos.length()-2);
		modos += " WHERE aj_id='"+aj_id+"'";
		sql+=modos;
		
		try {
			control.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modos(Ajanlat mod) throws SQLException {
		modos(mod.getAj_id(),mod.getLeiras(),mod.getAr(),mod.getCim(),mod.getTerulet(),mod.getElado(),mod.getHaz(),
				mod.getUdvar(),mod.getKert(),mod.getTerasz(),
				mod.getErkely(),mod.getMedence(),mod.getGarazs(),mod.getIdop(),
				mod.getHirdeto_nev());
	}
	
	public static void torol(int aj_id) throws SQLException {
		String sql = "DELETE FROM Ajanlat WHERE aj_id='"+aj_id+"'";
		DBControl control = new DBControl();
		try {
			control.delete(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

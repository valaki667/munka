package hu.ingatlankozvetites.dao;


public class DBConnect {

	public static final String dbfile = System.getenv("DB_path");
	private static DBConnect instance = null;
	
	public static final DBConnect getDAOInstance() {
		if (instance == null) {
			try {
				instance = new DBConnect();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	public DBConnect() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
	}
	public String getDbfile() {
		return dbfile;
	}
	
	//TODO adatbazis
}


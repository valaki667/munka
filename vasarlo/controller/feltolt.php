<?php

class adddata
 {
    public function add()
      {
        $servername = "localhost";
        $username = "admin123";
        $password = "admin123";
        $dbname = "vasarlohalmaz";

        try {
            $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
            $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sql = "INSERT INTO Vasarlo (email, name, password, cim)
            VALUES ('john@example.com', 'Doe', 'alma','6725,Szeged Valami utca 22');
            INSERT INTO Vasarlo (email, name, password, cim)
            VALUES ('john1@example.com', 'Doe2', 'alma','6725,Szeged Valami utca 22');
            INSERT INTO Vasarlo (email, name, password, cim)
            VALUES ('john2@example.com', 'Doe3', 'alma','6725,Szeged Valami utca 22')";
            // use exec() because no results are returned
            $conn->exec($sql);
              $sql = "INSERT INTO szallcimek (email, szallcim, elso)
            VALUES ('john@example.com','6725,Szeged Valami utca 22','1');
            INSERT INTO szallcimek (email, szallcim, elso)
            VALUES ('john@example.com','6725,Szeged Valami utca 24','0');
            INSERT INTO szallcimek (email, szallcim, elso)
            VALUES ('john2@example.com','6725,Szeged Valami utca 26','1')";
            // use exec() because no results are returned
            $conn->exec($sql);
              $sql = "INSERT INTO szamcimek (email, szamcim, adoszam, elso)
            VALUES ('john@example.com','6725,Szeged Valami utca 22','121231321-1-21','1');
            INSERT INTO szamcimek (email, szamcim, adoszam, elso)
            VALUES ('john@example.com','6725,Szeged Valami utca 23','','0');
            INSERT INTO szamcimek (email, szamcim, adoszam, elso)
            VALUES ('john1@example.com','6725,Szeged Valami utca 24','','1')";
            // use exec() because no results are returned
            $conn->exec($sql);
  echo "New record created successfully";
} catch(PDOException $e) {
  echo $sql . "<br>" . $e->getMessage();
}

$conn = null;

      }
      
      
  public function add2()
      {
        $servername = "localhost";
        $username = "admin123";
        $password = "admin123";
        $dbname = "vasarlohalmaz";

            try {
              $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
              $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
              $sql = "INSERT INTO Vasarlo (email, name, password, cim)
              VALUES ('john@example.com', 'Doe', 'alma','6725,Szeged Valami utca 22')";
              // use exec() because no results are returned
              $conn->exec($sql);
              echo "New record created successfully";
            } catch(PDOException $e) {
              echo $sql . "<br>" . $e->getMessage();
            }

            $conn = null;

                  }     
      
      
      
 }

?>

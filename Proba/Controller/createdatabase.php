<?php

class databasecreate
 {
      public function create()
      {
          require '.\konst.php';
          

        try {
    
        $conn = new PDO("mysql:host=$servername", $username, $password);

       $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
       try
       {
       $sql = "Drop DATABASE $dbname";
       $conn->exec($sql);
       }
       catch(PDOException $e)
       {
       }
    
       $sql = "CREATE DATABASE $dbname";
  
       $conn->exec($sql);
       $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
       $sql = "CREATE TABLE users (
        id INT(8) AUTO_INCREMENT NOT NULL,
        name VARCHAR(30) NOT NULL,
        PRIMARY KEY (id)
        )";
         $conn->exec($sql);
       $sql = "CREATE TABLE advertisements (
        id INT(8)AUTO_INCREMENT NOT NULL,
        userid INT(8) NOT NULL,
        title VARCHAR(30),
        PRIMARY KEY (id),
        FOREIGN KEY (userid) REFERENCES users(id)
        )";
           $conn->exec($sql);   
          echo "Adatbázis Sikeresen létrehozva az users és advertisements táblákkal együtt ";
      } catch(PDOException $e) {
          echo "nem sikerült létrehozni a következő hiba miatt:";
        echo $e->getMessage();
      }




      $conn = null;





      }
 }

?>
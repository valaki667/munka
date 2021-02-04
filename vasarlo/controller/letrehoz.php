<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 class databasecreate
 {
      public function create()
      {
        $servername = "localhost";
        $username = "admin123";
        $password = "admin123";
        $dbname = "vasarlohalmaz";
        try {
    
        $conn = new PDO("mysql:host=$servername", $username, $password);

       $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
       $sql = "Drop DATABASE vasarlohalmaz";
         $conn->exec($sql);
      $sql = "CREATE DATABASE vasarlohalmaz";
        //CONSTRAINT email FOREIGN KEY (email)
          //REFERENCES Vasarlo(email),
        $conn->exec($sql);
       $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
       $sql = "CREATE TABLE Vasarlo (
        email VARCHAR(50)NOT NULL,
        name VARCHAR(30) NOT NULL,
        password VARCHAR(30) NOT NULL,
        cim VARCHAR(30) NOT NULL,
        PRIMARY KEY (email)
        )";
         $conn->exec($sql);
       $sql = "CREATE TABLE szamcimek (
        email VARCHAR(50)NOT NULL,
        szamcim VARCHAR(30) NOT NULL,
        adoszam VARCHAR(30),
        elso BIT(1),
        FOREIGN KEY (email) REFERENCES Vasarlo(email)
        )";
           $conn->exec($sql);
        $sql = "CREATE TABLE szallcimek (
        email VARCHAR(50)NOT NULL,
        szallcim VARCHAR(30) NOT NULL,
        elso BIT(1),
        FOREIGN KEY (email) REFERENCES Vasarlo(email)
        )";
           $conn->exec($sql);   
          echo "Adatbázis Sikeresen létrehozva a Vasarló táblával együtt";
      } catch(PDOException $e) {
          echo "nem sikerült létrehozni a következő hiba miatt:";
        echo $e->getMessage();
      }




      $conn = null;





      }
 }

?> 



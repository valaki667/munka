<?php

 class torlo
 {
public function torolszallcim($param1,$param2)
      {
 $servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";

try {
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $sql = "DELETE FROM szallcimek WHERE email=? AND szallcim=?";
  // use exec() because no results are returned
$stmt= $conn->prepare($sql);
$stmt->execute([$param1,$param2]);

  echo "New record created successfully2";
} catch(PDOException $e) {
  echo $sql . "<br>" . $e->getMessage();
}

$conn = null;

      } 
      
      public function elsodleges($param1)
      {
           $servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";
$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  // set the PDO error mode to exception
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

  $sql = "UPDATE szallcimek SET elso=0 WHERE email=?";

    $stmt = $conn->prepare($sql);
   $stmt->execute([$param1]);
   // $stmt->execute();
        }


          
      
      
      
 }
 
 
 
 $torlo = new torlo();
 $torlo->torolszallcim($_GET['email'],$_GET['cim']);
 Echo "torolve";
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


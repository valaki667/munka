<?php
 class torlo
 {
public function torolmind($param1)
      {
 $servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";

try {
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $sql = "DELETE FROM szamcimek WHERE email=?";
  $stmt= $conn->prepare($sql);
$stmt->execute([$param1]);
  $sql = "DELETE FROM szallcimek WHERE email=?";
  $stmt= $conn->prepare($sql);
$stmt->execute([$param1]);
  $sql = "DELETE FROM Vasarlo WHERE email=?";
  $stmt= $conn->prepare($sql);
$stmt->execute([$param1]);
  // use exec() because no results are returned


  echo "New record created successfully2";
} catch(PDOException $e) {
  echo $sql . "<br>" . $e->getMessage();
}

$conn = null;

      } 
      
   


          
      
      
      
 }
 
 
 
 $torlo = new torlo();
 $torlo->torolmind($_GET['email']);
 Echo "torolve";
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


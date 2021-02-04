<?php

class addcim
 {
public function addcim2($param1,$param2,$param3,$param4)
      {
 $servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";

try {
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $sql = "INSERT INTO szamcimek (email, szamcim, adoszam, elso)
  VALUES (?, ?, ?,?)";
  // use exec() because no results are returned
$stmt= $conn->prepare($sql);
$stmt->execute([$param1,$param2,$param3,$param4]);

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

  $sql = "UPDATE szamcimek SET elso=0 WHERE email=?";

    $stmt = $conn->prepare($sql);
   $stmt->execute([$param1]);
   // $stmt->execute();
        }


          
      
      
      
 }
 
 if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['ment']))
    {
   
     $hozaado = new addcim();
     if ($_REQUEST['elso'] == 1){
     $hozaado->elsodleges($_REQUEST['oldemail']);
     }
     $hozaado->addcim2($_REQUEST['oldemail'],$_REQUEST['szamcim'],$_REQUEST['adoszam'],$_REQUEST['elso']);
     echo "sieker";
    }
 
 
 

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


<?php


class szerkeszt {
public function listazegyfo($param)
        {
        
$servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT * FROM Vasarlo WHERE email=?");
  $stmt->execute([$param]);     
    //$result = $stmt->fetchAll();
 /*foreach(new TableRows(new RecursiveArrayIterator($stmt->fetchAll())) as $k=>$v) {
    echo $v;
    echo "szerkeszt";
  }
*/
    
    while ($row = $stmt->fetch()) {
    //echo $row['email']."<br />\n";
         $dbdata[]=$row;
        
}

     return json_encode($dbdata);
        }
        
        

public function atir($param1,$param2,$param3,$param4,$param5)
        {
   $servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";
$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  // set the PDO error mode to exception
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

  $sql = "UPDATE Vasarlo SET email=? , name=? , password=?, cim =? WHERE email=?";

    $stmt = $conn->prepare($sql);
   $stmt->execute([$param1,$param2,$param3,$param4,$param5]);
   // $stmt->execute();
        }
}




if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['ment']))
    {
   
      $atiro = new szerkeszt();
      $atiro->atir($_REQUEST['email'],$_REQUEST['name'],$_REQUEST['password'],$_REQUEST['cim'],$_REQUEST['oldemail']);
      echo "sikeresen átírva";
    }

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


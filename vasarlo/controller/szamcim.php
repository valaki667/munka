<?php
   
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
class cimes {
public function cimekfo($param,$param2)
        {
        
$servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT * FROM szamcimek WHERE email=?");
  echo $param2;
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
}
        


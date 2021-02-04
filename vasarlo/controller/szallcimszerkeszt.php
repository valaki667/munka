<?php
class cimes {
public function cimekfo($param,$param2)
        {
        
$servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT * FROM szallcimek WHERE email=? AND szallcim=?");
  
  $stmt->execute([$param, $param2]);     
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
        
        
        public function atir($param1,$param2,$param3,$param4)
        {
   $servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";
$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  // set the PDO error mode to exception
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

  $sql = "UPDATE szallcimek SET email=? , szallcim=?, elso =? WHERE email=? AND szallcim=?";

    $stmt = $conn->prepare($sql);
   $stmt->execute([$param1,$param2,$param3,$param1,$param4]);
   // $stmt->execute();
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




if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['ment']))
    {
   
      $atiro = new cimes();
      if ($_REQUEST['elso']==1){
          $atiro->elsodleges($_REQUEST['oldemail']);
      }
      $atiro->atir($_REQUEST['oldemail'],$_REQUEST['szallcim'],$_REQUEST['elso'],$_REQUEST['oldcim']);
      echo "sikeresen átírva";
    }
        
       

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


<?php
//echo "<table style='border: solid 1px black;'>";
//echo "<tr><th>Email</th><th>név</th><th>jelszó</th><th>cím</th><th></th></tr>";
class TableRows extends RecursiveIteratorIterator {
  function __construct($it) {
    parent::__construct($it, self::LEAVES_ONLY);
  }

  function current() {
    return "<td style='width:150px;border:1px solid black;'>" . parent::current(). "</td> ";
  }

  function beginChildren() {
    echo "<tr>";
  }

  function endChildren() {
    echo "</tr>" .  "\n";
  }
} 
class vasarlolist 
{
    public function listaz()
        {
        
$servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT * FROM Vasarlo");
  $stmt->execute();     
  //  $result = $stmt->setFetchMode(PDO::FETCH_ASSOC); 
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
        
public function listazegyfo($param)
        {
        
$servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT * FROM Vasarlo WHERE email=" .$param);
  $stmt->execute();     
    $result = $stmt->setFetchMode(PDO::FETCH_ASSOC); 
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
        
       
        
       public function listazjson()
        {
        
$servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT * FROM Vasarlo");
  $stmt->execute();     
    $result = $stmt->setFetchMode(PDO::FETCH_ASSOC); 
    $dbdata = array();
 foreach(new TableRows(new RecursiveArrayIterator($row = $stmt->fetchAll())) as $k=>$v) {
   $dbdata[]=$row;

  }

    return json_encode($dbdata);
    }
    
    
    
    
}
         
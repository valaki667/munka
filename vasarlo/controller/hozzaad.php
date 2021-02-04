<?php
class addadat
 {
public function add2($param1,$param2,$param3,$param4)
      {
 $servername = "localhost";
$username = "admin123";
$password = "admin123";
$dbname = "vasarlohalmaz";

try {
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $sql = "INSERT INTO Vasarlo (email, name, password, cim)
  VALUES (?, ?, ?,?)";
  // use exec() because no results are returned
$stmt= $conn->prepare($sql);
$stmt->execute([$param1,$param2,$param3,$param4]);

  echo "New record created successfully";
} catch(PDOException $e) {
  echo $sql . "<br>" . $e->getMessage();
}

$conn = null;

      }     
      
 }
 
 if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['ment']))
    {
     $hozaado = new addadat();
     $hozaado->add2($_REQUEST['email'],$_REQUEST['name'],$_REQUEST['password'],$_REQUEST['cim']);
     echo "sieker";
    }
 
 
 

?>
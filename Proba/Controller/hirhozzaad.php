<?php
class addadat
 {
public function add2($param1,$param2)
      {
  require '..\konst.php';

try {
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $sql = "INSERT INTO advertisements (title,userid)
  VALUES (?,?)";
  // use exec() because no results are returned
 $stmt= $conn->prepare($sql); 
 $stmt->execute([$param1,$param2]);

 
} catch(PDOException $e) {
  echo $sql . "<br>" . $e->getMessage();
}

$conn = null;

      }     
      
 }
 
 if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['ment']))
    {
     $hozaado = new addadat();
    
     $hozaado->add2($_REQUEST['title'],$_POST['select1']);
     echo "sieker";
    }
 
 
 

?>



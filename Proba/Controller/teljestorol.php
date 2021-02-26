<?php
class torlo
 {
public function torolmind($param1)
      {
  require '..\konst.php';

try {
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $sql = "DELETE FROM advertisements WHERE userid=?";
  $stmt= $conn->prepare($sql);
$stmt->execute([$param1]);
  $sql = "DELETE FROM users WHERE id=?";
  $stmt= $conn->prepare($sql);
$stmt->execute([$param1]);


} catch(PDOException $e) {
  echo $sql . "<br>" . $e->getMessage();
}

$conn = null;

      } 
 }
  
 $torlo = new torlo();
 $torlo->torolmind($_GET['id']);
 Echo "torolve";
 ?>



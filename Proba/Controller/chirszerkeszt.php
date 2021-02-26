<?php


class szerkeszt {
public function listazegyfo($param)
        {
     require '..\konst.php';
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT title,userid From advertisements WHERE userid=?");
  $stmt->execute([$param]);     
  
   while ($row = $stmt->fetch()) 
   {
           $dbdata[]=$row;   
}
     return json_encode($dbdata);
        }
        
        
public function atir($param1,$param2)
        {
    require '..\konst.php';
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

  $sql = "UPDATE advertisements SET title=? WHERE userid=?";
  $stmt = $conn->prepare($sql);
  $stmt->execute([$param1,$param2]);
   
        }
}




if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['ment']))
    {
   
      $atiro = new szerkeszt();
      $atiro->atir($_REQUEST['title'],$_REQUEST['id']);
      echo "sikeresen átírva";
    }
?>


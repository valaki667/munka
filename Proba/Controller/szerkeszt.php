<?php

class szerkeszt {
public function listazegyfo($param)
        {
     require '..\konst.php';
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT * FROM users WHERE id=?");
  $stmt->execute([$param]);     
  
   while ($row = $stmt->fetch()) 
   {
           $dbdata[]=$row;   
}
     return json_encode($dbdata);
        }
        
        
public function atir($param1,$param2,$param3)
        {
    require '..\konst.php';
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

  $sql = "UPDATE users SET id=? , name=? WHERE id=?";
  $stmt = $conn->prepare($sql);
  $stmt->execute([$param1,$param2,$param3]);
   
        }
}




if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['ment']))
    {
   
      $atiro = new szerkeszt();
      $atiro->atir($_REQUEST['id'],$_REQUEST['name'],$_REQUEST['oldid']);
      echo "sikeresen átírva";
    }
?>

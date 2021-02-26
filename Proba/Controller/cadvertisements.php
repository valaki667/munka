<?php
class advertisementslist
{
     
    public function advertisementslist()
        {
          require '..\konst.php';

  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT advertisements.title,users.name,users.id FROM advertisements,users WHERE advertisements.userid=users.id");
  $stmt->execute();   
  
  while ($row = $stmt->fetch()) 
    {
         $dbdata[]=$row;      
    }
    
 return json_encode($dbdata);
        
        
        }
}
?>
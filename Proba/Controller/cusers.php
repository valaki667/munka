<?php
class userlistaz
{
     
    public function userlist()
        {
          require '..\konst.php';

  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $stmt = $conn->prepare("SELECT * FROM users");
  $stmt->execute();   
  
  while ($row = $stmt->fetch()) 
    {
         $dbdata[]=$row;      
    }
    
 return json_encode($dbdata);
        
        
        }
}
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


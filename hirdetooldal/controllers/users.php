<?php
  // show all user
 require_once "../models/databaseconnect.php";  
 require_once "../views/userview.php";  
 $list = new userlist();             
 $list2 = $list->userlistt2();
 
 
  while($row = mysqli_fetch_array($list2))
            {
                echo  $row['id'];
                echo "&emsp;";
                echo  $row['name'] ;
                echo "<p></p>";
            }
         echo "</p>";
 
        
  
     
       require_once "../views/userview2.php";  //home button

  


        

?>
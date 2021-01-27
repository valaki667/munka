<?php
   
require_once "./models/databaseconnect.php";  //library


//show the selected user
class person
{
   public function personid($param) 
   {
       
        echo "<p>";
        echo "ID";
        echo "&emsp;";
        echo "name";
        echo "<p>";
        
        $egy =new userlist();
        $list2 = $egy->userlistt3($param);  //data save to variables
   // show datafiles
       while($row = mysqli_fetch_array($list2))
        { 
                echo  $row['id'];
                echo "&emsp;";
                echo  $row['name'] ;
                echo "<p></p>";
        }
         echo "</p>";

return;
    }

}


?>




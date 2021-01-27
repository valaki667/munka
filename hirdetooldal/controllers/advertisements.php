<?php

require_once "../models/databaseconnect.php";   //include library
require_once "../views/advertisementsview.php"; 

$list = new advertisements();
$list2 = $list->advertisementslist2();     //create advertisements data


// SHOW  advertisements
while($row = mysqli_fetch_array($list2))
{
              
                echo  $row['id'] ;
                echo "&emsp;";
                echo   "<a href='../".$row['userid']."'>" . $row['userid'] . "</a>" ;
                echo "&emsp;";
                echo  $row['title'] ;
                echo "<p></p>";
        
         
}
        

echo "</p>";

require_once "../views/userview2.php";  //back button, return home

?>


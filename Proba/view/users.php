<?php
 require_once '../Controller/cusers.php';
 
 $adat = new userlistaz();
 $jdata=$adat->userlist();
 $jjdata = json_decode($jdata);
 
 echo '<table border="1">';
 echo "<tr><th>ID</th><th>Name</th><th></th><th></th></tr>";
 foreach ($jjdata as $stand) {
         
              echo "<tr>";
           echo "<td>$stand->id</td>";
           echo "<td>$stand->name</td>";
           echo "<td>" . "<a href='../view/szerkeszto.php?id=" . $stand->id .  "'>" . "szerkesztes" . "</td>";
            echo "<td>" . "<a href='../controller/teljestorol.php?id=" . $stand->id .  "'>" . "Törlés" . "</td>";
           echo "</tr>";
            }

echo "</table>";
echo'<form action="../view/hozzaad.php" method="post">';
 echo  '<td> <input type="submit" name="hozzaad" value="hozzaad" ></td>';
   echo "</form>";

 
 
 ?>
 



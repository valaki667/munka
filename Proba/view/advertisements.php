<?php
 require_once '../Controller/cadvertisements.php';
 
 $adat = new advertisementslist();
 $jdata=$adat->advertisementslist();
 $jjdata = json_decode($jdata);
 
 echo '<table border="1">';
 echo "<tr><th>titles</th><th>Name</th><th></th><th></th></tr>";
 foreach ($jjdata as $stand) {
         
              echo "<tr>";
           echo "<td>$stand->title</td>";
           echo "<td>$stand->name</td>";
           echo "<td>" . "<a href='../view/hirszerkeszt.php?id=" . $stand->id .  "'>" . "szerkesztes" . "</td>";
            echo "<td>" . "<a href='../controller/teljestorol.php?id=" . $stand->id .  "'>" . "Törlés" . "</td>";
           echo "</tr>";
            }

echo "</table>";
echo'<form action="../view/hirhozzaad.php" method="post">';
 echo  '<td> <input type="submit" name="hozzaad" value="hozzaad" ></td>';
   echo "</form>";

 
 
 ?>
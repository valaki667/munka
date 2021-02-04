<?php

include '..\controller\vasarlolista.php';

$lista = new vasarlolist();
$jdata =$lista->listaz();
$jjdata = json_decode($jdata);
echo '<table border="1">';
echo "<tr><th>Email</th><th>név</th><th>jelszó</th><th>cím</th><th></th><th></th><th></th><th></th></tr>";
foreach ($jjdata as $stand) {
         
              echo "<tr>";
           echo "<td>$stand->email</td>";
           echo "<td>$stand->name</td>";
           echo "<td>$stand->password</td>";
           echo "<td>$stand->cim</td>";
           echo "<td>" . "<a href='../view/szerkezto.php?" . $stand->email .  "'>" . "szerkesztes" . "</td>";
           echo "<td>" . "<a href='../view/szamcim.php?" . $stand->email .  "'>" . "Számlázási cím" . "</td>";
           echo "<td>" . "<a href='../view/szallcim.php?" . $stand->email .  "'>" . "Szállítási cím" . "</td>";
            echo "<td>" . "<a href='../controller/teljestorol.php?email=" . $stand->email .  "'>" . "Törlés" . "</td>";
           // echo "<td> ." "<a href='../".$row['userid']."'>" . $row['userid'] . "</a>" ;</td>';
           echo "</tr>";
            }

echo "</table>";
echo'<form action="../view/hozzaad.php" method="post">';
 echo  '<td> <input type="submit" name="hozzaad" value="hozzaad" ></td>';
   echo "</form>";

//echo $lista->listazjson();


<?php
include '..\controller\szallcim.php';
$url = $_SERVER['REQUEST_URI'];
$valtozo = parse_url($url,PHP_URL_QUERY);

echo $valtozo ."  felhasználóhoz tartozó szállízási címek";

$cimek = new cimes();
$jdata = $cimek->cimekfo($valtozo);

$jjdata = json_decode($jdata);

echo '<table border="1">';
echo "<tr><th>Szállítási cím</th><th>elsődleges</th><th></th><th></th></tr>";
foreach ($jjdata as $stand) {
  
              echo "<tr>";
           echo "<td>$stand->szallcim</td>";
           echo "<td>$stand->elso</td>";
          
           echo "<td>" . "<a href='../view/szallcimszerkeszt.php?email=" . $stand->email. "&cim=". $stand->szallcim. "'>" . "szerkesztes" . "</td>";
            echo "<td>" . "<a href='../controller/torlo.php?email=" . $stand->email. "&cim=". $stand->szallcim. "'>" . "törlés" . "</td>";
         ;
           // echo "<td> ." "<a href='../".$row['userid']."'>" . $row['userid'] . "</a>" ;</td>';
           echo "</tr>";
            }

echo "</table>";
echo'<form action="../view/szallcimhozzaad.php?'.$valtozo.'" method="post">';
 echo  '<td> <input type="submit" name="hozzaad" value="hozzaad" ></td>';
   echo "</form>";
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


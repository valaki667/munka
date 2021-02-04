<?php
 include '..\controller\szamcim.php';
$url = $_SERVER['REQUEST_URI'];
$valtozo = parse_url($url,PHP_URL_QUERY);
$valtozo2 = parse_url($url,PHP_URL_FRAGMENT);
echo $valtozo ."  felhasználóhoz tartozó számlázási címek";

$cimek = new cimes();
$jdata = $cimek->cimekfo($valtozo,$valtozo2);

$jjdata = json_decode($jdata);

echo '<table border="1">';
echo "<tr><th>Számlázásí cím</th><th>adószám</th><th>elsődleges</th><th></th><th></th></tr>";
foreach ($jjdata as $stand) {
  
              echo "<tr>";
           echo "<td>$stand->szamcim</td>";
           echo "<td>$stand->adoszam</td>";
           echo "<td>$stand->elso</td>";
          
           echo "<td>" . "<a href='../view/szamszerkeszt.php?email=" . $stand->email   . "&cim=" . $stand->szamcim. "'>" . "szerkesztes" . "</td>";
            echo "<td>" . "<a href='../controller/torloszamcim.php?email=" . $stand->email   . "&cim=" . $stand->szamcim. "'>" . "törlés" . "</td>";
         ;
           // echo "<td> ." "<a href='../".$row['userid']."'>" . $row['userid'] . "</a>" ;</td>';
           echo "</tr>";
            }

echo "</table>";
echo'<form action="../view/szamcimhozzaad.php?'.$valtozo.'" method="post">';
 echo  '<td> <input type="submit" name="hozzaad" value="hozzaad" ></td>';
   echo "</form>";




/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


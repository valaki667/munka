<?php
include '..\controller\szallcim.php';
$url = $_SERVER['REQUEST_URI'];
$valtozo = parse_url($url,PHP_URL_QUERY);
$cimek = new cimes();





echo'<form action="../controller/szallcimhozzaad.php" method="post">';
   echo '<input hidden="true" id="oldemail" name="oldemail" value="' . $valtozo. '"></input>' ;
echo '<table border="1">';
echo "<tr><th>szállítázási cím</th><th>első</th><th></th><th></th></tr>";

 echo '<tr><td><input type="text" id="szallcim" name="szallcim"></td>';
 echo '<td><input type="text" id="elso" name="elso" ></td>';


 echo  '<td> <input type="submit" name="ment" value="mentes" ></td>';
  echo  '<td><input type="submit" name="elvet" value="elvetes" > </td></tr>';
        
        
        echo "</table>";
        echo "</form>";
        
        
        

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


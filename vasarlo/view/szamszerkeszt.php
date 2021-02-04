<?php

 include '..\controller\szamszerkeszt.php';
$url = $_SERVER['REQUEST_URI'];
//$valtozo = parse_url($url,PHP_URL_QUERY);
$valtozo= $_GET['email'];
//$valtozo2 = parse_url($url,PHP_URL_FRAGMENT);
$valtozo2=$_GET['cim'];
$cimek = new cimes();

$jdata = $cimek->cimekfo($valtozo,$valtozo2);

$jjdata = json_decode($jdata);


foreach ($jjdata as $stand) {

echo'<form action="../controller/szamszerkeszt.php" method="post">';
   echo '<input hidden="true" id="oldemail" name="oldemail" value='. $stand->email.'></input>' ;
    echo '<input hidden="true" id="oldcim" name="oldcim" value='. $valtozo2.'></input>' ;
echo '<table border="1">';
echo "<tr><th>számlázási cím</th><th>adószám</th><th>első</th><th></th><th></th></tr>";

 echo '<tr><td><input type="text" id="szamcim" name="szamcim" value=" '.$stand->szamcim.'"></td>';
 echo '<td><input type="text" id="adoszam" name="adoszam" value=" '.$stand->adoszam.'"></td>';
 echo '<td><input type="text" id="elso" name="elso" value=" '.$stand->elso.'"></td>';


 echo  '<td> <input type="submit" name="ment" value="mentes" ></td>';
  echo  '<td><input type="submit" name="elvet" value="elvetes" > </td></tr>';
        
        
        echo "</table>";
        echo "</form>";
        
        
        
}






/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


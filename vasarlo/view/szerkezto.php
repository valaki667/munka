<?php

     include '..\controller\szerkeszt.php';

echo "ez a szerkeszto oldal";
$url = $_SERVER['REQUEST_URI'];
$valtozo = parse_url($url,PHP_URL_QUERY);

$vasarlo = new szerkeszt();
$jdata = $vasarlo->listazegyfo($valtozo);

$jjdata = json_decode($jdata);
//print_r($jjdata);
foreach ($jjdata as $stand) {

echo'<form action="../controller/szerkeszt.php" method="post">';
   echo '<input hidden="true" id="oldemail" name="oldemail" value='. $stand->email.'></input>' ;
echo '<table border="1">';
echo "<tr><th>Email</th><th>név</th><th>jelszó</th><th>cím</th><th></th><th></th></tr>";

 echo '<tr><td><input type="text" id="email" name="email" value=" '.$stand->email.'"></td>';
 echo '<td><input type="text" id="name" name="name" value=" '.$stand->name.'"></td>';
 echo '<td><input type="text" id="password" name="password" value=" '.$stand->password.'"></td>';
 echo '<td><input type="text" id="cim" name="cim" value=" '.$stand->cim.'"></td>';

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


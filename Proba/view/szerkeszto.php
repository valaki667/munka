<?php
include '..\controller\szerkeszt.php';

echo "ez a szerkeszto oldal";
$url = $_SERVER['REQUEST_URI'];
$valtozo= $_GET['id'];

$users = new szerkeszt();
$jdata = $users->listazegyfo($valtozo);
$jjdata = json_decode($jdata);

foreach ($jjdata as $stand) {

echo'<form action="../controller/szerkeszt.php" method="post">';
echo '<input hidden="true" id="oldid" name="oldid" value='. $stand->id.'></input>' ;
echo '<table border="1">';
echo "<tr><th>id</th><th>name</th><th></th><th></th></tr>";
 echo '<tr><td><input type="text" id="id" name="id" value=" '.$stand->id.'"></td>';
 echo '<td><input type="text" id="name" name="name" value=" '.$stand->name.'"></td>';
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


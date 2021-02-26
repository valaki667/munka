<?php
include '..\controller\chirszerkeszt.php';

echo "ez a szerkeszto oldal";
$url = $_SERVER['REQUEST_URI'];
$valtozo= $_GET['id'];

$users = new szerkeszt();
$jdata = $users->listazegyfo($valtozo);
$jjdata = json_decode($jdata);

foreach ($jjdata as $stand) {

echo'<form action="../controller/chirszerkeszt.php" method="post">';
echo '<input hidden="true" id="id" name="id" value='. $stand->userid.'></input>' ;
echo '<table border="1">';
echo "<tr><th>title</th><th></th><th></th></tr>";
 echo '<tr><td><input type="text" id="title" name="title" value=" '.$stand->title.'"></td>';
 echo  '<td> <input type="submit" name="ment" value="mentes" ></td>';
  echo  '<td><input type="submit" name="elvet" value="elvetes" > </td></tr>';
        
        
        echo "</table>";
        echo "</form>";
        
        
        
}
?>


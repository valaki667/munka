<?php

include '..\controller\hozzaad.php';
 
echo'<form action="../controller/hozzaad.php" method="post">';
echo '<table border="1">';
echo "<tr><th>name</th><th></th><th></th></tr>";

 echo '<tr><td><input type="text" id="name" name="name" ></td>';
 echo  '<td> <input type="submit" name="ment" value="mentes" ></td>';
  echo  '<td><input type="submit" name="elvet" value="elvetes" > </td></tr>';
        
        
        echo "</table>";
        echo "</form>";

?>


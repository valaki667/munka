<?php

 include '..\controller\hozzaad.php';
 
echo'<form action="../controller/hozzaad.php" method="post">';
echo '<table border="1">';
echo "<tr><th>Email</th><th>név</th><th>jelszó</th><th>cím</th><th></th><th></th></tr>";

 echo '<tr><td><input type="text" id="email" name="email" ></td>';
 echo '<td><input type="text" id="name" name="name" ></td>';
 echo '<td><input type="text" id="password" name="password"></td>';
 echo '<td><input type="text" id="cim" name="cim" ></td>';

 echo  '<td> <input type="submit" name="ment" value="mentes" ></td>';
  echo  '<td><input type="submit" name="elvet" value="elvetes" > </td></tr>';
        
        
        echo "</table>";
        echo "</form>";

?>

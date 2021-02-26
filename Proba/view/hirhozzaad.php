<?php
include '..\controller\hirhozzaad.php';
  require_once '../Controller/cusers.php';
 
 $adat = new userlistaz();
 $jdata=$adat->userlist();
 $jjdata = json_decode($jdata);
echo'<form action="../controller/hirhozzaad.php" method="post">';
echo '<table border="1">';
echo "<tr><th>title</th><th>user</th><th></th><th></th></tr>";

 echo '<tr><td><input type="text" id="title" name="title" ></td>';
 //echo '<td><input type="text" id="userid" name="userid" ></td>';
 echo '<td><select name="select1" size=”1”> ' ;

  foreach ($jjdata as $stand) {
         
             
       echo '<option value="'.$stand->id.'">'.$stand->name.'</option>';
         
              
            }
 
 
       //  . '<option value="pc">Komplett PC</option>
     // <option value="laptop">Laptop</option>
     // <option value="alaplap" selected>Alaplap</option>'
    
  echo  '</select></td>';
 
 echo  '<td> <input type="submit" name="ment" value="mentes" ></td>';
  echo  '<td><input type="submit" name="elvet" value="elvetes" > </td></tr>';
        
        
        echo "</table>";
        echo "</form>";

?>

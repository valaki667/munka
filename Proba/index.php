<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->


<html>
    <head>
        <link rel="kinezet" type="text/css" href="kinezet.css">
        <meta charset="UTF-8">
           <style>
        body { background-color: YellowGreen; }
        p { color: #fff; }
    </style>
        <title></title>
    </head>
    <body>
     
        <?php

        require_once './Controller/createdatabase.php';
         require_once './Controller/feltolt.php';

        //adatbázis létrehozása gombra kattintáskor
        if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['letrehozo']))
           {
            $kezd = new databasecreate(); 
            $kezd->create(); 
           }
      
            if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['feltolt']))
           {
            $add = new adddata(); 
            $add->add(); 
           }
        ?>
        
        <form action="index.php" method="post">
    <input type="submit" name="letrehozo" value="Adatbázis Létrehozás" />
    </form>
        <form action="index.php" method="post">
    <input type="submit" name="feltolt" value="Adatbázis Feltöltése teszt adatokkal" />
    </form>
           <form action="./view/users.php" method="post">
    <input type="submit" name="users" value="Felhasználók listázása" />
    </form>
        <form action="./view/advertisements.php" method="post">
    <input type="submit" name="advertisements" value="Hirdetések Listázása" />
    </form>
        
        
    </body>
</html>

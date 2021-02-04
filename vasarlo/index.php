<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <form action="index.php" method="post">
    <input type="submit" name="letrehozo" value="Adatbázis Létrehozás" />
    </form>
  <form action="index.php" method="post">
    <input type="submit" name="vasarlo" value="Vásárlók listázása" />
    </form>
        <?php
        $fp = fopen('logfile.txt', 'a');
fwrite($fp, 'itt lesz a log');
fclose($fp);
        $url = $_SERVER['REQUEST_URI'];
        $url2 = $_SERVER['REQUEST_URI'];
           $url2 = $_SERVER['PHP_SELF'];
           
//echo $valtozo = parse_url($url,PHP_URL_QUERY);
//echo $valtozo2 = parse_url($url2,PHP_URL_FRAGMENT);
//echo $valtozo;
//echo $valtozo2;
//echo "<p>";
//echo $_GET['alma'];
//echo $_GET['korte'];

//echo $url2;

        
        
        
        
        // put your code here
        include '.\controller\hozzaad.php';
         include '.\controller\letrehoz.php';
           include '.\controller\router.php';
                include '.\controller\feltolt.php';
         
                
   if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['feltolt']))
{
       $feltolt =new adddata();
       $feltolt->add();
echo "adatbázis feltöltve";
require_once "controller/ures.php"; 
}
        
         
         
        if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['letrehozo']))
    {
            $kezd = new databasecreate(); 
        //header("Location:view/vasarlolistazas.php");
 
  
       $kezd->create(); 
     
     echo'<form action="index.php" method="post">
    <input type="submit" name="feltolt" value="Adatbázis feltöltés" />';
    }
      if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['vasarlo']))
    {
       header("Location:../vasarlo/view/vasarlolistazas.php");
    }

         
        ?>
    </body>
</html>

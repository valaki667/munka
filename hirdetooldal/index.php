<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Hirdetesek</title>
    </head>
    <body>
   
   <?php
   include "controllers/person.php";     //include library
   require "controllers/first.php";      //include library
   
   error_reporting(0); //  url index undefinite at start
   $url = $_GET['url'];  
   
   $kezd = new first();  //instantiation object
   $kezd->screen();     // call fucntion that creates the home screen

   //if there is display the selected user
   if ($url != "")
   {
      $ffix =$url; 
      $fix = new person();        //instantiation object    
      $fix->personid($ffix);      // call fucntion that creates and show the selected userdata
   }
    ?>
    </body>
</html>

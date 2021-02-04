<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
echo "üres oldal elkapás nélkül";
if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['ures']))
{
echo "üres oldal";
}
?>

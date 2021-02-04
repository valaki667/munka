<?PHP
echo '<form action="index.php" method="post">
    <input type="submit" name="feltolt" value="Adatbázis feltöltés" />';

  
 if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['feltolt']))
{
echo "adatok feltoltve";
}   
    

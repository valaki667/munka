<?php

class adddata
 {
    public function add()
      {
 require '.\konst.php';

        try {
            $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
            $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sql = "INSERT INTO users (name)
            VALUES ('TEszt Elek');
            INSERT INTO users (name)
            VALUES ('Próba János');
            INSERT INTO users (name)
            VALUES ('Sikeres Patrik')";
            // use exec() because no results are returned
            $conn->exec($sql);
              $sql = "INSERT INTO advertisements (title, userid)
            VALUES ('Kiskutya eladó','1');
            INSERT INTO advertisements (title, userid)
            VALUES ('Lovaskocsi kölcsönözhetető','2')";
            // use exec() because no results are returned
            $conn->exec($sql);
             
  echo "Pár teszt adat feltöltve";
} catch(PDOException $e) {
  echo $sql . "<br>" . $e->getMessage();
}

$conn = null;

      }
      
 }
?>

 <?php 


 
 // select from user table
class userlist 
{
    public function userlistt2()
        {
         $mysqli = new mysqli("localhost","alma","alma","hir");  // owner mysql database  
         
        // Check connection
         if ($mysqli -> connect_errno)
            {
                echo "Failed to connect to MySQL: " . $mysqli -> connect_error;
                exit();
            }

        $resoult = $mysqli->query('SELECT * FROM users'); //

        return $resoult;  
        }     
    
        //select 1 changed user
    public function userlistt3($filter)
         {
         $mysqli = new mysqli("localhost","alma","alma","hir");  // owner mysql database  
        // Check connection
         if ($mysqli -> connect_errno) 
         {
            echo "Failed to connect to MySQL: " . $mysqli -> connect_error;
            exit();
         }

        $filter = "SELECT * FROM users WHERE id=".$filter;    //make query with variable
        $resoult = $mysqli->query($filter);
           
        
         return $resoult;  
         }
    
        
    //Select All user
    public function userlistt()
        {
        $mysqli = new mysqli("localhost","alma","alma","hir");  // owner mysql database
        // Check connection
        if ($mysqli -> connect_errno) 
        {
            echo "Failed to connect to MySQL: " . $mysqli -> connect_error;
            exit();
        }       
        $resoult = $mysqli->query('SELECT * FROM users');   //show userlist
         while($row = mysqli_fetch_array($resoult)){
            echo "<tr>";
                echo "<td>" . $row['id'] . "</td>";
                echo "<td>" . $row['name'] . "</td>";
        
            echo "</tr>";
        }
        echo "</table>";
        // Free result set
        mysqli_free_result($resoult);
        }
    
    //fejlesztési tartalék , adatok felvitele az adatbázisba    
    public function userlistfeltolt($param1, $param2)
    {
        $mysqli = new mysqli("localhost","alma","alma","hir");  // owner mysql database  
        // Check connection
        if ($mysqli -> connect_errno) 
        {
            echo "Failed to connect to MySQL: " . $mysqli -> connect_error;
            exit();
        } 
        
        $filter = "INSERT INTO `users`(`id`, `name`) VALUES (".$param1.",'".$param2."')";
        $resoult = $mysqli->query($filter);
            
        return $resoult;  
    }
}


//select from advertisement
 class advertisements 
 {
    public function advertisementslist2()
    {
        $mysqli = new mysqli("localhost","alma","alma","hir");  // owner mysql database  
         // Check connection
        if ($mysqli -> connect_errno) 
        {
        echo "Failed to connect to MySQL: " . $mysqli -> connect_error;
        exit();
        }
        
        $resoult = $mysqli->query('SELECT * FROM advertisements');
   
        return $resoult;  
    }
    
 }
 

?> 


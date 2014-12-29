<?php
ini_set('max_execution_time', 120); //300 seconds = 5 minutes

class database {
	private $db;

	function __construct($type,$host,$port,$name,$username,$password){
		try {
		 switch($type){
                                case 'postgres':
                                        $this->db = new PDO('pgsql:host='.$host.';port='.$port.';dbname='.$name.';user='.$username.';password='.$password);
                                        break;
                                case 'sqlite':
                                        $this->db = new PDO('sqlite:'.$name);
                                        break;
                                default:
                                        echo "not known database type\n";
                                        break;
                        }

		} catch(Exception $e){
			echo "oops..".$e->getMessage()."\n";
			exit;
		}
	}

	function doQuery($query){
		$stmt = $this->db->query($query);
		if(!$stmt){
			$arr = $this->db->errorInfo();
			echo $arr[2];
			return false;
		}
		return $stmt;
	}

	function doPrepare($query){
		$stmt = $this->db->prepare($query);
		if(!$stmt){
			$arr = $this->db->errorInfo();
			return false;
		}
		return $stmt;
	}

	function doExecute($stmt,$params){
		if(!$stmt->execute($params)){
			$arr = $stmt->errorInfo();
			return false;
		}
		return $stmt;
	}

}

if(!isset($_GET['s']) || !isset($_GET['ex'])){
	echo "params not set";
}

$mydb = new database("sqlite","",0,"/opt/lampp/htdocs/graphstemp/funderarxivNEW.db","","");

$query = "select GrantId1, GrantId2, Acr1 as acr1, Acr2 as acr2, Similarity, F1 as f1, F2 as f2, P1, P2 from grantsimilarityview where Similarity>? and ExperimentId=?";

$start_all = microtime(true);
$memory_start_all = memory_get_usage();
$stmt = $mydb->doPrepare($query);
$end_all = microtime(true);
$memory_end_all = memory_get_usage();

$start_all01 = microtime(true);
$memory_start_all01 = memory_get_usage();
$stmt = $mydb->doExecute($stmt,array($_GET['s'],$_GET['ex']));
$end_all01 = microtime(true);
$memory_end_all01 = memory_get_usage();

$start_all02 = microtime(true);
$memory_start_all02 = memory_get_usage();
$list = $stmt->fetchAll();
$end_all02 = microtime(true);
$memory_end_all02 = memory_get_usage();



// echo 'Result List : <br/>fetchAll : '.($end_all-$start_all).'s, '.($memory_end_all-$memory_start_all).'b<br/>';
// echo 'Result List : <br/>fetchAll : '.($end_all01-$start_all01).'s, '.($memory_end_all01-$memory_start_all01).'b<br/>';
// echo 'Result List : <br/>fetchAll : '.($end_all02-$start_all02).'s, '.($memory_end_all02-$memory_start_all02).'b<br/>';
// echo 'Result List : <br/>fetchAll : '.($end_all02-$start_all).'s, '.($memory_end_all02-$memory_start_all).'b<br/>';

echo json_encode($list);
?>

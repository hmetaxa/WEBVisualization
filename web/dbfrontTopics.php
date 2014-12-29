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

/******* nikos added*******/
$start_all = microtime(true);
$memory_start_all = memory_get_usage();
/*******end nikos added*******/
$stmt = $mydb->doPrepare($query);
/******* nikos added*******/
$end_all = microtime(true);
$memory_end_all = memory_get_usage();
/*******end nikos added*******/

/******* nikos added*******/
$start_all01 = microtime(true);
$memory_start_all01 = memory_get_usage();
/*******end nikos added*******/
$stmt = $mydb->doExecute($stmt,array($_GET['s'],$_GET['ex']));
////$stmt->execute(array($_GET['s'],$_GET['ex']));
/******* nikos added*******/
$end_all01 = microtime(true);
$memory_end_all01 = memory_get_usage();
/*******end nikos added*******/

//$stmt = $mydb->doQuery($query);
//if($stmt != false){
/******* nikos added*******/
$start_all02 = microtime(true);
$memory_start_all02 = memory_get_usage();
/*******end nikos added*******/
	$list = $stmt->fetchAll();
/******* nikos added*******/
$end_all02 = microtime(true);
$memory_end_all02 = memory_get_usage();
/*******end nikos added*******/

	//echo "\nBefore: ".count($list)."\n";
	//echo json_encode($list);
/*}
else{
	echo "empty";
	exit;
}*/
/* get experiments */



/////nmp start
// $query = "select distinct ExperimentId from TopicAnalysis";

// /******* nikos added*******/
// $start_all1 = microtime(true);
// $memory_start_all1 = memory_get_usage();
// /*******end nikos added*******/

// $stmt = $mydb->doQuery($query);
// $experiments = $stmt->fetchAll(PDO::FETCH_NUM);

// /******* nikos added*******/
// $end_all1 = microtime(true);
// $memory_end_all1 = memory_get_usage();
// /*******end nikos added*******/


// /* get topics per project */
// $query = "select GrantId, TopicId, AVG(Weight) as weight from TopicsPerDoc Inner join GrantPerDoc on TopicsPerDoc.DocId=GrantPerDoc.DocId where Weight>0.02 AND ExperimentId=? Group By GrantId, TopicId Order by GrantId, AVG(Weight) Desc";
// $grants = array();

// /******* nikos added*******/
// $start_all2 = microtime(true);
// $memory_start_all2 = memory_get_usage();
// /*******end nikos added*******/

// //$stmt = $mydb->doQuery($query);
// $stmt = $mydb->doPrepare($query);
// $stmt = $mydb->doExecute($stmt,array($_GET['ex']));

// $res = $stmt->fetch();
// do {
// 	if(!isset($grants[$res[0]]))
// 		$grants[$res[0]] = array();
// 	if(count($grants[$res[0]])>3)
// 		continue;
// 	array_push($grants[$res[0]],array("topic"=>$res[1],"weight"=>$res[2]));	
// } while ($res = $stmt->fetch());


// /*******  nikos added*******/
// $end_all2 = microtime(true);
// $memory_end_all2 = memory_get_usage();
// /*******end nikos added*******/

// //print_r($grants);

// /* get Keywords per topic, keep the first 10 */
// $query = "select TopicId, Item, Counts From TopicAnalysis where ItemType=1 and ExperimentId=? and Counts>100 Order By TopicID ASC, counts DESC";

// $topics = array();

// /******* nikos added*******/
// $start_all3 = microtime(true);
// $memory_start_all3 = memory_get_usage();
// /*******end nikos added*******/

// //$stmt = $mydb->doQuery($query);
// $stmt = $mydb->doPrepare($query);
// $stmt = $mydb->doExecute($stmt,array($_GET['ex']));
// $res = $stmt->fetch();
// do {
// 	if(!isset($topics[$res[0]]))
// 		$topics[$res[0]] = array();
// 	if(count($topics[$res[0]])>9)
// 		continue;
// 	array_push($topics[$res[0]],array("item"=>$res[1],"counts"=>$res[2]));	
// } while ($res = $stmt->fetch());


// /******* nikos added*******/
// $end_all3 = microtime(true);
// $memory_end_all3 = memory_get_usage();
/////nmp end



// echo 'Result List : <br/>fetchAll : '.($end_all-$start_all).'s, '.($memory_end_all-$memory_start_all).'b<br/>';
// echo 'Result List : <br/>fetchAll : '.($end_all01-$start_all01).'s, '.($memory_end_all01-$memory_start_all01).'b<br/>';
// echo 'Result List : <br/>fetchAll : '.($end_all02-$start_all02).'s, '.($memory_end_all02-$memory_start_all02).'b<br/>';
// echo 'Result List : <br/>fetchAll : '.($end_all02-$start_all).'s, '.($memory_end_all02-$memory_start_all).'b<br/>';
//echo 'Result experiments : <br/>fetchAll1 : '.($end_all1-$start_all1).'s, '.($memory_end_all1-$memory_start_all1).'b<br/>';
//echo 'Result grants: <br/>fetchAll2 : '.($end_all2-$start_all2).'s, '.($memory_end_all2-$memory_start_all2).'b<br/>';
//echo 'Result topics: <br/>fetchAll3 : '.($end_all3-$start_all3).'s, '.($memory_end_all3-$memory_start_all3).'b<br/>';
/*******end nikos added*******/

//print_r($topics);

//echo "After: ".count($list)."\n";


/////nmp start
//$everything = array();
//$everything['resp'] = $list;
$everything = $list;		//added this
// $everything['grants'] = $grants;
// $everything['topics'] = $topics;
// $everything['expers'] = $experiments;
/////nmp end

//echo "End: ".json_encode($everything['resp'])."\n";
//echo "End \n";
echo json_encode($everything);

?>

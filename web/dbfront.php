<?php
ini_set('max_execution_time', 120); //300 seconds = 5 minutes

//ini_set('memory_limit', '-1');		// unlimited memory
ini_set('memory_limit', '512M');	
// Require Library
/////require_once("../../phpfastcache/phpfastcache/phpfastcache.php");
/////phpFastCache::setup("storage","auto"); 	// try "files" instead of "auto"
// or change auto to: files, sqlite, memcache, memcached, xcache, wincache, apc or simple with “auto”...etc

// simple Caching with:
/////$cache = phpFastCache();


class database {
	private $db,$last_query = null;

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
		/* http://dev.mybb.com/issues/1276 */
		if($this->last_query)
		{
			$this->last_query->closeCursor();
		}
		$stmt = $this->db->query($query);
		$this->last_query = $query;
		if(!$stmt){
			$arr = $this->db->errorInfo();
			echo $arr[2];
			return false;
		}
		return $stmt;
	}

	function doPrepare($query){
		if($this->last_query)
		{
			$this->last_query->closeCursor();
		}
		$stmt = $this->db->prepare($query);
		$this->last_query = $query;
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

if(!isset($_GET['s']) || !isset($_GET['ex']) || !isset($_GET['type'])){
	echo "params not set";
}

// Try to get $products from Caching First
// $key is "identity keyword";
/////$key = $_GET['ex'].$_GET['type'];
// echo "key is ".$key."\n\n";
/////$products = $cache->get($key);
// echo "products is ".$products."\n\n";

//$mydb = new database("sqlite","",0,"/opt/lampp/htdocs/graphstemp/funderarxivNEW.db","","");
$mydb = new database("sqlite","",0,"/opt/lampp/htdocs/graphstemp/fundedarxiv_19062014.db","","");
//$mydb = new database("sqlite","",0,"/opt/lampp/htdocs/graphstemp/funderarxiv062014.db","","");

if($_GET['type']=="Graph"){
/////	if($products == null) {
		// $query = "select GrantId1, GrantId2, Acr1 as acr1, Acr2 as acr2, Similarity, F1 as f1, F2 as f2, P1, P2 from grantsimilarityview where Similarity>? and ExperimentId=?";
		$query = "select * from EntitySimilarityView where ExperimentId=?  and  Similarity>?";
		// palio
		// $query = "select g.EntityId1 as EntityId1,g.EntityId2 as EntityId2,g.Similarity as Similarity,g.ExperimentId as ExperimentId,grCnts1.grantsCnt as P1,grCnts2.grantsCnt as P2,gr1.acronym as Acr1,gr2.acronym as Acr2,gr1.funding_lvl2 as F1,gr2.funding_lvl2 as F2 FROM EntitySimilarity g INNER JOIN Grant gr1 ON gr1.GrantId = g.EntityId1 And g.EntityType=1 and gr1.funding_lvl0='FP7' and ExperimentId=? and Similarity>? INNER JOIN Grant gr2 ON gr2.GrantId = g.EntityId2 And g.EntityType=1 and gr2.funding_lvl0='FP7' and ExperimentId=? and Similarity>? INNER JOIN(select GrantId, count(*) as grantsCnt from GrantPerDoc group by  grantId having count(*) >2) grCnts1 on grCnts1.grantId = g.EntityId1 INNER JOIN (select GrantId, count(*) as grantsCnt from GrantPerDoc group by grantId having count(*) >2) grCnts2 on grCnts2.grantId = g.EntityId2";
		$start_all = microtime(true);
		$memory_start_all = memory_get_usage();
		$stmt = $mydb->doPrepare($query);
		$end_all = microtime(true);
		$memory_end_all = memory_get_usage();

		$start_all01 = microtime(true);
		$memory_start_all01 = memory_get_usage();
		$stmt = $mydb->doExecute($stmt,array($_GET['ex'],$_GET['s']));
		//palio
		// $stmt = $mydb->doExecute($stmt,array($_GET['ex'],$_GET['s'],$_GET['ex'],$_GET['s']));
		$end_all01 = microtime(true);
		$memory_end_all01 = memory_get_usage();

		//$stmt = $mydb->doQuery($query);
		//if($stmt != false){
		$start_all02 = microtime(true);
		$memory_start_all02 = memory_get_usage();
		$list = $stmt->fetchAll();
		$end_all02 = microtime(true);
		$memory_end_all02 = memory_get_usage();

		$products = $list;
		// echo 'Result List : <br/>fetchAll : '.($end_all-$start_all).'s, '.($memory_end_all-$memory_start_all).'b<br/>';
		// echo 'Result List : <br/>fetchAll : '.($end_all01-$start_all01).'s, '.($memory_end_all01-$memory_start_all01).'b<br/>';
		// echo 'Result List : <br/>fetchAll : '.($end_all02-$start_all02).'s, '.($memory_end_all02-$memory_start_all02).'b<br/>';
		// echo 'Result List : <br/>fetchAll : '.($end_all02-$start_all).'s, '.($memory_end_all02-$memory_start_all).'b<br/>';

		// Write products to Cache in 10 minutes with same keyword
		/////$cache->set($key,$products,600);
/////	}
	echo json_encode($products);
}
elseif($_GET['type']=="Experiments"){
/////	if($products == null) {
		$query = "select distinct ExperimentId from TopicAnalysis";

		$start_all1 = microtime(true);
		$memory_start_all1 = memory_get_usage();
		$stmt = $mydb->doQuery($query);
//		$stmt = $mydb->doPrepare($query);
// commented below because of conjuction with phpfastcache
		$experiments = $stmt->fetchAll(PDO::FETCH_NUM);
//		$experiments = $stmt->fetchAll();
		$end_all1 = microtime(true);
		$memory_end_all1 = memory_get_usage();
		//echo 'Result experiments : <br/>fetchAll1 : '.($end_all1-$start_all1).'s, '.($memory_end_all1-$memory_start_all1).'b<br/>';

		$products = $experiments;
		// Write products to Cache in 10 minutes with same keyword
		/////$cache->set($key,$products,600);
/////	}
	echo json_encode($products);
}
elseif($_GET['type']=="Grants"){
/////	if($products == null) {
		/* get topics per project */
		$query = "select GrantId, TopicId, AVG(Weight) as weight from TopicsPerDoc Inner join GrantPerDoc on TopicsPerDoc.DocId=GrantPerDoc.DocId where Weight>0.02 AND ExperimentId=? Group By GrantId, TopicId Order by GrantId, AVG(Weight) Desc";
		$grants = array();

		$start_all2 = microtime(true);
		$memory_start_all2 = memory_get_usage();

		//$stmt = $mydb->doQuery($query);
		$stmt = $mydb->doPrepare($query);
		$stmt = $mydb->doExecute($stmt,array($_GET['ex']));

		$res = $stmt->fetch();
		do {
			if(!isset($grants[$res[0]]))
				$grants[$res[0]] = array();
			if(count($grants[$res[0]])>3)
				continue;
			array_push($grants[$res[0]],array("topic"=>$res[1],"weight"=>$res[2]));	
		} while ($res = $stmt->fetch());

		$end_all2 = microtime(true);
		$memory_end_all2 = memory_get_usage();

		$products = $grants;
		// Write products to Cache in 10 minutes with same keyword
		/////$cache->set($key,$products,600);
		//print_r($grants);
		//echo 'Result grants: <br/>fetchAll2 : '.($end_all2-$start_all2).'s, '.($memory_end_all2-$memory_start_all2).'b<br/>';
/////	}	
	echo json_encode($products);
}
elseif($_GET['type']=="Topics"){
/////	if($products == null) {
		/* get Keywords per topic, keep the first 10 */
		// palio
		// $query = "select TopicId, Item, Counts From TopicAnalysis where ItemType<>1 and ExperimentId=? and Counts>100 Order By TopicID ASC, counts DESC";
		$query = "select * from topicdescriptionview where ExperimentId=?";
		$topics = array();

		$start_all3 = microtime(true);
		$memory_start_all3 = memory_get_usage();
		//$stmt = $mydb->doQuery($query);
		$stmt = $mydb->doPrepare($query);
		$stmt = $mydb->doExecute($stmt,array($_GET['ex']));
		$res = $stmt->fetch();
		do {
			if(!isset($topics[$res[0]]))
				$topics[$res[0]] = array();
			if(count($topics[$res[0]])>9)
				continue;
			// den ta pairnw ola apo katw... mono kapoia apo auta.
			array_push($topics[$res[0]],array("item"=>$res[1],"counts"=>$res[7]));	
		} while ($res = $stmt->fetch());
		$end_all3 = microtime(true);
		$memory_end_all3 = memory_get_usage();
		//echo 'Result topics: <br/>fetchAll3 : '.($end_all3-$start_all3).'s, '.($memory_end_all3-$memory_start_all3).'b<br/>';
		$products = $topics;
		// Write products to Cache in 10 minutes with same keyword
		/////$cache->set($key,$products,600);
/////	}
	echo json_encode($products);
}

?>

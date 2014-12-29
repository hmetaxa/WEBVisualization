
var relations = [];								
var names = [];								
var subdConnections = [];
var subdBiConnections = [];
var subdConnectionsNum = [];
var subdBiConnectionsNum = [];


function isNodeConnected(linkedByIndex, a, b) {
	return linkedByIndex[a.index + "," + b.index] || linkedByIndex[b.index + "," + a.index] || a.index == b.index;
}


function isNodeDirectConnected(a, b) {
	return linkedByIndex[a.index + "," + b.index] || a.index == b.index;
}


/* test function is similar to fade function*/
function createJsonFile(linkedByIndex,nodeCircles){

	nodeCircles.each(function(mynode) {
		var areaIndex = subdConnections.indexOf(mynode.area)
		if(areaIndex != -1){	// if already exists
			subdConnectionsNum[areaIndex]++;
		}
		else{
			subdConnections.push(mynode.area);
			areaIndex = subdConnections.indexOf(mynode.area)
			subdConnectionsNum[areaIndex] = 1;

			subdBiConnections.push(areaIndex)
			subdBiConnections[areaIndex] = []			// area saving

			subdBiConnectionsNum.push(areaIndex)
			subdBiConnectionsNum[areaIndex] = []		// indexOf_area saving

		}

		nodeCircles.each(function(d) {
			if (isNodeConnected(linkedByIndex, mynode, d)) {
				if (d != mynode){
					var areaBiIndex = subdBiConnections[areaIndex].indexOf(d.area)
					if(areaBiIndex != -1){	// if already exists
						subdBiConnectionsNum[areaIndex][areaBiIndex]++;
					}
					else{
						subdBiConnections[areaIndex].push(d.area);
						areaBiIndex = subdBiConnections[areaIndex].indexOf(d.area)
						subdBiConnectionsNum[areaIndex][areaBiIndex] = 1;
					}
				}
			}
		})
	})

  	for (var i = 0; i < subdConnections.length; i++) {
	  	for (var j = 0; j < subdConnections.length; j++) {
			if(subdBiConnectionsNum[i][j] === undefined){
				console.log("un")				
				subdBiConnectionsNum[i][j] = 0
			}
	  	}
	}

	console.log("subdConnections:"+subdConnections)
	console.log("subdConnectionsNum:"+subdConnectionsNum)
/*	console.log("subdBIConnections:"+subdBiConnections)
	console.log("subdBIConnectionsNum:"+subdBiConnectionsNum)
*/

//creating the JSON file for the 2nd layout (Chord)
	var string = "["

  	for (var i = 0; i < subdConnections.length-1; i++) {
  		string += "["
	  	for (var j = 0; j < subdConnections.length-1; j++) {
	  		string += subdBiConnectionsNum[i][j]+","
	  	}
  		string += subdBiConnectionsNum[i][subdConnections.length-1]+"],"	// the last one inner []
  	}
	string += "["			// the last one outer []
  	for (var j = 0; j < subdConnections.length-1; j++) {
  		string += subdBiConnectionsNum[subdConnections.length-1][j]+","
  	}
	string += subdBiConnectionsNum[subdConnections.length-1][subdConnections.length-1]+"]]"	// the last one inner [] of the outer []


///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////// USE BELOW LATER THAT IT WILL BE ON GRAPH 	///////////////
//     var jsonObject = {
//         "metros" : [],
//         "routes" : []
//     };


// // write cities to JSON Object
// for ( var index = 0; index < graph.getVerticies().length; index++) {
//     /* do not yet convert to JSON here */
//     jsonObject.metros[index] = graph.getVertex(index).getData();
// }

// // write routes to JSON Object
// for ( var index = 0; index < graph.getEdges().length; index++) {
//     /* do not yet convert to JSON here */
//     jsonObject.routes[index] = graph.getEdge(index);
// }
////////////////////////////////////////////////////////////////////////////////////////////
	// some jQuery to write to file
	$.ajax({
	    type : "POST",
		async: true,
	    url : "http://astero.di.uoa.gr/graphstemp/fileCreator.php",
	    dataType : 'text',		// this is json if we put it like this JSON object 
	    data : {
	/*        json : JSON.stringify(jsonObject) /* convert here only */
			func : "json",			// declare the function you want to use from fileCreator.php
			json : string
	    },
		success: function(){
		      console.log("JSON file Created")
		},
		error: function(e){
			alert('Error: ' + JSON.stringify(e));
		}

	});

	console.log("JSON:"+string)

}


var hexDigits = new Array
        ("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"); 

//Function to convert hex format to a rgb color
function rgb2hex(rgb) {
 rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
 return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
}

function hex(x) {
  return isNaN(x) ? "00" : hexDigits[(x - x % 16) / 16] + hexDigits[x % 16];
 }


function createCSVFile(clrArray){
	var max_proj = 0;
	var string = "name,color,projects,relations";
	var subdSum = 0;

  	for (var i = 0; i < subdConnections.length; i++) {

		if(subdConnectionsNum[i] > max_proj)
			max_proj = subdConnectionsNum[i];


	  	subdSum = 0;
	  	for (var j = 0; j < subdConnections.length; j++) {
	  		subdSum += subdBiConnectionsNum[i][j]
		}
		relations.push(subdSum);
	};


  	for (var i = 0; i < subdConnections.length; i++) {
		string += "\n"+subdConnections[i]+","+rgb2hex(clrArray[i])+","+subdConnectionsNum[i]+","+relations[i];
	}


	$.ajax({
	    type : "POST",
		async: true,
	    url : "http://astero.di.uoa.gr/graphstemp/fileCreator.php",
	    dataType : 'text',		// this is json if we put it like this JSON object 
	    data : {
	/*        json : JSON.stringify(jsonObject) /* convert here only */
			func : "csv",
			csv : string
	    },
		success: function(){
		    console.log("CSV file Created")
		},
		error: function(e){
			alert('Error: ' + e);
		}

	});
}



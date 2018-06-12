
function changecolor(object) {
	if (object.style.backgroundColor=='#66ff00') { 
	        object.style.backgroundColor='#FFFFFF';
	} else {
	        object.style.backgroundColor='#66FF00';
	}
}

function originalcolor(object) {
	object.style.backgroundColor='#ffffff';
}

function overcolor(object) {	
	if (object.style.backgroundColor=='#66ff00') { return; } 
	object.style.backgroundColor='#ffff99';
}

function outcolor(object) {
	if (object.style.backgroundColor=='#66ff00') { return; }
	object.style.backgroundColor='#ffffff';
}

function bgblinkRandomColor() {
//           var colorArray=["#ffcccc","#ffffcc"];
             var color="#ccffcc|#ffcccc|#ffbbbb|#bbff00|#00ffbb";
             var colorArray=color.split("|"); 	     
	     var icolor=parseInt(Math.random() * colorArray.length);
             var mytag = document.getElementsByTagName( "*" );
             for (var e = 0; e < mytag.length; e++) {
                 if ( mytag[e].className == "bgblink" ) {
		      mytag[e].style.backgroundColor = colorArray[icolor];
                 }
             }
}

function blinkRandomColor() { 
//      var color="#f00|#0f0|#00f|#880|#808|#088|yellow|green|blue|gray"; 
        var color="red|blue|yellow|green|white|gray|black|#880|#808|#088"; 
        var colorArray=color.split("|"); 
	var icolor=parseInt(Math.random() * colorArray.length);
        var mytag = document.getElementsByTagName( "*" );
        for (var e = 0; e < mytag.length; e++) {
             if ( mytag[e].className == "blink" ) {
	          mytag[e].style.color = colorArray[icolor];
             }
        }	
} 

function ClassBlink( nClass, sColor ) { 
             var colorArray=[];
             colorArray.push(sColor);     
             colorArray.push(sColor);     
	     colorArray.push("white");     
	     var icolor=parseInt(Math.random() * colorArray.length);
             var mytag = document.getElementsByTagName( "*" );
             for (var e = 0; e < mytag.length; e++) {
                  if ( mytag[e].className == nClass ) {
	               mytag[e].style.color = colorArray[icolor];
                  }
             }	
} 

function ClassBGBlink( nClass, sColor ) {
             var colorArray=[];
             colorArray.push(sColor);     
             colorArray.push(sColor);     
	     colorArray.push("white");     
	     var icolor=parseInt(Math.random() * colorArray.length);
             var mytag = document.getElementsByTagName( "*" );
             for (var e = 0; e < mytag.length; e++) {
                 if ( mytag[e].className == nClass ) {
		      mytag[e].style.backgroundColor = colorArray[icolor];
                 }
             }
}


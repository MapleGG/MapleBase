/**
 * 	for tspl print machine
	author:孙丽森
 */


jQuery.support.cors = true

next = false;

function showResult(data){next=true;}



//指定电脑端的输出口
function openport(){
	//同步访问command接口的openport路径打开端口号？
	$.ajax({			
		url:"http://localhost:8999/openport",
		async:false,//同步		
		type: "get",
		success:function(data){},
		error:function(data){}	
	});
}
//给打印机发送指令
function sendcommand(comm){	
	$.ajax({			
		url:"http://localhost:8999/sendcommand/"+comm,
		async:false,		
		type: "get",
		success:function(data){},
		error:function(data){}
	})
}

//关闭打印机端口
function closeport(){	
	$.ajax({			
		url:"http://localhost:8999/closeport",
		async:false,		
		type: "get",
		success:function(data){},
		error:function(data){}		
	})	
}


function nobackfeed(){	
	$.ajax({	
		
		url:"http://localhost:8999/nobackfeed",
		async:false,		
		type: "get",
		success:function(data){
			//alert(data.result);
		},
		error:function(data){
			//alert("fail");
		}
	})

	nextStep();
}
//清除缓存
function clearbuffer(){		
	$.ajax({	
		url:"http://localhost:8999/clearbuffer",
		async:false,		
		type: "get",
		success:function(data){},
		error:function(data){}		
	})
	nextStep();	
}
//设定打印方向和是否镜像
function direction(direction, mirror){		
	$.ajax({	
		url:"http://localhost:8999/direction/"+direction+"/"+mirror,
		async:false,		
		type: "get",
		success:function(data){},
		error:function(data){}	
	})	
}


function printlabel(){	
	$.ajax({	
		
		url:"http://localhost:8999/printlabel",
		async:false,		
		type: "get",
		success:function(data){
			
			//alert(data.result);
		},
		error:function(data){
			
			//alert("fail");
		}		
	})
	
}


//文字打印
function windowsfont(x,y,height,rotation,shape,
		underline,fontname,content){	
	$.ajax({			
		url:encodeURI("http://localhost:8999/windowsfont/"+x+"/"+y+"/"
				+height+"/"+rotation+"/"+shape+"/"+underline+"/"
				+fontname+"/"+content),
		async:false,		
		type: "get",
		success:function(data){},
		error:function(data){}	
	})
}


function setup(labelWidth,labelHeight, speed, density, gapOrBlack,
		distance, offset){	
	
	$.ajax({			
		url:"http://localhost:8999/setup/"+labelWidth+"/"+labelHeight+"/"
				+speed+"/"+density+"/"+gapOrBlack+"/"+distance+"/"
				+offset,
		async:false,		
		type: "get",
		success:function(data){
			//alert(data.result);
		},
		error:function(data){
			
			//alert("fail");
		}		
	})

	
}


function barcode(x, y,codeType, height,humanReadble,rotation,
		narrow, wide, content){
	
	$.ajax({			
		url:"http://localhost:8999/barcode/"+x+"/"+y+"/"
				+codeType+"/"+height+"/"+humanReadble+"/"+rotation+"/"
				+narrow+"/"+wide+"/"+content,
		async:false,		
		type: "get",
		success:function(data){
				//alert(data.result);
		},
		error:function(data){
				//alert("fail");
		}	
	})
	
}


//发送绘制矩形指令BOX
function box(x_start, y_start, x_end, y_end, lineThick){	
	
	$.ajax({			
		url:"http://localhost:8999/box/"+x_start+"/"+y_start+"/"
				+x_end+"/"+y_end+"/"+lineThick,
		async:false,		
		type: "get",
		success:function(data){
			//alert(data.result);
		},
		error:function(data){
				//alert("fail");
		}	
	})
	
}


function circle(x, y, diameter, lineThick){		
	$.ajax({			
		url:"http://localhost:8999/circle/"+x+"/"+y+"/"
				+diameter+"/"+lineThick,
		async:false,		
		type: "get",
		success:function(data){},
		error:function(data){}	
	})	
}


function downloadpcx(src, dest){	
	
	$.ajax({			
		url:"http://localhost:8999/downloadpcx/"+src+"/"+dest,
		async:false,		
		type: "get",
		success:function(data){
			
			//alert(data.result);
		},
		error:function(data){
			
			//alert("fail");
		}		
	})
	
}

function putpcx(x,  y, filename){	
	
	$.ajax({			
		url:"http://localhost:8999/putpcx/"+x+"/"+y+"/"
				+filename,
		async:false,		
		type: "get",
		success:function(data){
			
			//alert(data.result);
		},
		error:function(data){
		}		
	})

	nextStep();
	
}

function nextStep(){
	
	var j=0;
	for(var i=0; i< 50000; i++){
		
		if(next){
			
			break;
		}
		
		
	}	
	next=false;	
}



//打印条码
function printBarCode(mtlSnSn,mtlSnParts,isChecked,area,loc1,loc2,loc3,version){
	
    var font = "Arial";//文字样式
    var barCodeCode = "128M";//字符集属性设定
	//单位 mm
	var labelWidth = "60";//条码长度6cm
	var labelHeight = "22";//条码宽度2.2cm
    var mtlSnSnH = 60;//全局高度
    var mtlSnSnY = 45;//全局Y轴
    // 38  30
    var fontH = 38;
     
    var x1 = 20;
    var x2 = x1+180*2;
    var x3 = 630;

    var shape = 2;//字体外形
		
    console.log("储位1"+loc1);
    
	loc1 = locFormat(loc1);//重新排列储位号码
	loc2 = locFormat(loc2);//重新排列储位号码
	loc3 = locFormat(loc3);//重新排列储位号码
	
	if(""==loc1) {
		//如果储位1为空
		if(""==loc2) {
			//如果储位2为空
			//储位3赋值到储位1，储位3设为空字符串
			loc1=loc3;
			loc3="";
		}else {
			//否则储位2赋1，储位3赋2，储位3赋空
			loc1=loc2;
			loc2=loc3;
			loc3="";
		}			
	}else {		
		if(""==loc2) {
			//如果储位2为空，储位1不为空
			//储位3赋值储位2，储位3设空
			loc2=loc3;
			loc3="";
		}
	}
	//截取料号后两位
	var s = mtlSnParts.substring(mtlSnParts.length-2);
	
	var s1 = mtlSnSn.substring(0, 3);//截取唯一码前3位UAA	
	var s2 = mtlSnSn.substring(3,6);//截取唯一码3-6位月和日
	var year=mtlSnSn.substring(6,7);//截取年份出来	
	var num=mtlSnSn.substring(7,12);//截取流水码
	var s3 = mtlSnSn.substring(12);//截取唯一码流水码最大一位
	var mtlSnSnShow = s1+s2+"-"+year+num+"-"+s3;//唯一码打印排列
	//console.log("唯一码打印："+mtlSnSnShow);
	
	openport();//打开端口
	
	//设定标签的宽度、高度、打印速度、打印浓度、感应器类别、Gap/Black mark垂直间距、Gap/Black mark偏移距离
    setup(labelWidth, labelHeight, "6", "14", "0", "2", "0");  
  
    sendcommand("SET TEAR ON");  //发送内建指令到条码打印机。//SET TEAR ON是否将撕纸位置移动到撕纸处

    clearbuffer();   //清除缓存
    
    direction(1, 0);//设定打印方向和是否镜像
	
    //box(x1, mtlSnSnY-15, 680, mtlSnSnY-9, 6);//发送绘制矩形指令BOX(20,15,680,15,6)

    barcode(x1, mtlSnSnY+5, barCodeCode, mtlSnSnH, 0, 0, 3, 4, mtlSnSn);//发送绘制一维码指令BARCODE使用条码机内部条码打印
    
    if(version && version != ""){
    	//x1右偏移560，mtlSnSnY下偏移6
        windowsfont(x1+580,mtlSnSnY+6, fontH+8, 0, shape, 0, font, version);//使用Windows Arial字体打印文字。只打印文字使用，打印版本文字
    }
   
    var mtlSnSnFontY = mtlSnSnH+mtlSnSnY;
    windowsfont(x1,mtlSnSnFontY+10, fontH, 0, shape, 0, font, mtlSnSnShow);//打印唯一码文字
    windowsfont(x1+130,mtlSnSnFontY+28,fontH,0,shape,0,font,"~~~~~~");//打印唯一码下面的波浪线
    if(area!=null&&area!=''){    	
        windowsfont(x2, mtlSnSnFontY+10, fontH+10, 0, shape, 0, font, area.substring(0,5));//打印吊位文字
        windowsfont(x2+110,mtlSnSnFontY,fontH+40,0,shape,0,font,area.substring(5));
    }
    
    
    circle(x3-20, mtlSnSnFontY+10, 2*fontH+5, 5);  //画圆，里面包含可能包含供改字样
    if("1"==isChecked) {//如果没有贴原料号或没有扫出	    
    	var boxoffset=18;   	  	
	    windowsfont(x3-boxoffset+5, mtlSnSnFontY+fontH-5, fontH, 0, shape, 0, font, "供改"); //打印“供改”文字
    }
    
    var chuWeiY=mtlSnSnFontY+fontH+30;
    var chuWeiOffset = 200;
    var lineYoff = 3+fontH;
    var lineXoff = 45;
    
    
    if(loc1!=''&&loc1!=null){
    	 //打印储位1
        /*windowsfont(x1, chuWeiY-10, fontH+10, 0, shape, 0, font, loc1[0]);
        windowsfont(x1+lineXoff+5, chuWeiY-10,fontH+10,0,shape,0,font,loc1[1]);
        windowsfont(x1+lineXoff+10,chuWeiY+15,fontH+5,0,shape,0,font,"~");
        windowsfont(x1+lineXoff*2,chuWeiY-10,fontH+10,0,shape,0,font,loc1[2]);
        windowsfont(x1+lineXoff*3,chuWeiY-10,fontH+10,0,shape,0,font,loc1[3]);*/
    	windowsfont(x1,chuWeiY-10,fontH+10,0,shape,0,font,loc1);
    	windowsfont(x1+lineXoff+30,chuWeiY+15,fontH+5,0,shape,0,font,"~");
    }
    if(loc2!=''&&loc2!=null){
    	//打印储位2
    	//windowsfont(x1+lineXoff*4-10,chuWeiY-10,fontH+10,0,shape,0,font,"=");
    	windowsfont(x1+lineXoff*5-10,chuWeiY-10,fontH+10,0,shape,0,font," = "+loc2);
    	windowsfont(x1+lineXoff*7+15,chuWeiY+15,fontH+5,0,shape,0,font,"~");
    	/*windowsfont(x1+lineXoff*4+30,chuWeiY-10,fontH+10,0,shape,0,font,loc2[0]);
    	windowsfont(x1+lineXoff*6-15,chuWeiY-10,fontH+10,0,shape,0,font,loc2[1]);
    	windowsfont(x1+lineXoff*6-10,chuWeiY+15,fontH+5,0,shape,0,font,"~");
    	windowsfont(x1+lineXoff*7-15,chuWeiY-10,fontH+10,0,shape,0,font,loc2[2]);
    	windowsfont(x1+lineXoff*8-15,chuWeiY-10,fontH+10,0,shape,0,font,loc2[3]);*/
    }
    if(loc3!=''&&loc3!=null){
    	//打印储位3
    	//windowsfont(x1+lineXoff*8+10,chuWeiY-10,fontH+10,0,shape,0,font,",");
    	windowsfont(x1+lineXoff*9-10,chuWeiY-10,fontH+10,0,shape,0,font," = "+loc3);
    	windowsfont(x1+lineXoff*10,chuWeiY+15,fontH+5,0,shape,0,font,"~");
    	/*windowsfont(x1+lineXoff*9+20,chuWeiY-10,fontH+10,0,shape,0,font,loc3[0]);
    	windowsfont(x1+lineXoff*11-30,chuWeiY-10,fontH+10,0,shape,0,font,loc3[1]);
    	windowsfont(x1+lineXoff*11-25,chuWeiY+15,fontH+5,0,shape,0,font,"~");
    	windowsfont(x1+lineXoff*12-30,chuWeiY-10,fontH+10,0,shape,0,font,loc3[2]);
    	windowsfont(x1+lineXoff*13-30,chuWeiY-10,fontH+10,0,shape,0,font,loc3[3]);*/
    }
    
    //料号一行
    windowsfont(x1, mtlSnSnFontY+2*fontH+35, fontH, 0, shape, 0, font, mtlSnParts); 

    
    windowsfont(x1+100, mtlSnSnFontY+3*fontH+15, fontH, 0, shape, 0, font, "~~~~~~");
  
    
	//料号条码
    //barcode(x1, mtlSnSnFontY+2*fontH+fontH-8-4+10, barCodeCode, mtlSnSnH-22, 0, 0, 3, 4, mtlSnParts);
    var boxoffset=18;  
    box(x1+590, mtlSnSnFontY+2*fontH+20, x1+590-boxoffset+100,mtlSnSnFontY+2*fontH+70, 5);//方框，里面可能包含专字样
    if("X"==s1.substring(2)) {   	 	
    	windowsfont(x1+620, mtlSnSnFontY+3*fontH-15, fontH+5, 0, shape, 0, font, "接");
    }

	
    var markX = x1+280;
    
    /*if("00"==s || "10"==s || "0R"==s) {
	    windowsfont(markX,mtlSnSnFontY+3*fontH-5, 50-8, 0, shape, 0, font, "★");  

	}else if("08"==s || "18"==s) {
	    windowsfont(markX,mtlSnSnFontY+3*fontH-5, 80-8, 0, shape, 0, font, "●"); 
 
	}else if("LB"==s) {
	    windowsfont(markX,mtlSnSnFontY+3*fontH-5, 70-8, 0, shape, 0, font, "■");  

	}else{
	    windowsfont(markX,mtlSnSnFontY+3*fontH-5, 40-8, 0, shape, 0, font, "△");  

	}*/
 
    printlabel();  
    
    
    closeport();
    
}


function locFormat(loc){
	//如果储位为空则设为空字符串
	var locs = [];
	if(loc==null){
		loc="";
	}
	//如果储位不为空字符串则截取前两位
	var locTwo = ""==loc?"":loc.substring(0, 2);
	//如果前两位为A4或P4则设为空字符串
	if("A4"==locTwo || "P4"==locTwo) {			
		loc="";
	}
	//如果储位不为空则分割为4块
	//查看唯一码编码手册对应中间一行
	if(""!=loc) {
		locs[0]=loc.substring(0,2)+" ";
		locs[1]=loc.substring(2,3)+" ";
		locs[2]=loc.substring(3,5)+" ";
		locs[3]=loc.substring(5)+" ";		
		//loc=s1+" "+s2+" "+s3+" "+s4;
		loc=locs[0]+locs[1]+locs[2]+locs[3];
		console.log(loc);
	}
	//返回重新排列的储位
	return loc;
}


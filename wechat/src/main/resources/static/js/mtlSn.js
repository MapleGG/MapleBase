var successColor = "#BBFFFF";
var errorColor = "#EE5C42";

var xmlhttp;
if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp = new XMLHttpRequest();
} else {// code for IE6, IE5
	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}


/*错误时样式变更*/
function playError() {
	document.getElementById("error1").src = "http://192.168.16.20/images/sfc-err.wav";
	document.getElementById("error2").src = "http://192.168.16.20/images/sfc-err.wav";
	document.getElementById("content").style.background = errorColor;
}

/*成功时样式变更*/
function playSuccess() {
	document.getElementById("error1").src = "http://192.168.16.20/images/sfc-ok.wav";
	document.getElementById("error2").src = "http://192.168.16.20/images/sfc-ok.wav";
	document.getElementById("content").style.background = successColor;
	document.getElementById("MTL_SN_DESC").focus();//变换焦点
}

/*工号输入后异步请求查看该员工是否存在*/
function verifyEmp(emp) {

	var emp_no = emp.value;
	xmlhttp.open("GET", "verifyEmp?EMPLOYEE_SCODE=" + emp_no, true);
	xmlhttp.send();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var content = xmlhttp.responseText;
			var employeeJSON = eval("(" + content + ")");
			if (employeeJSON.user == "0") {
				emp.value = "";
				document.getElementById("op_code").innerHTML = "工号不正确";
				document.getElementById("EMPLOYEE_SCODE").focus();
				playError();

			} else {
				document.getElementById("op_code").innerHTML = employeeJSON.employeeScode
						+ "-" + employeeJSON.employeeName;
				document.getElementById("MTL_SN_DESC").focus();
				document.getElementById("content").style.background = successColor;
				document.getElementById("MTL_SN_DESC").focus();
			}
		}
	}
}

$(function() {
	
	/*输入工号后按下回车键更换焦点*/
	$("#EMPLOYEE_SCODE").keydown(function() {
		if (window.event.keyCode == 13) {
			$("#MTL_SN_DESC").focus();
			return false;
		}
	});

	/*输入条码后按下回车获取条码并在条码后面添加“,”号*/
	$("#MTL_SN_DESC").keydown(function() {
		if (window.event.keyCode == 13) {
			var value = $("#MTL_SN_DESC").val();
			$("#MTL_SN_DESC").val(value + ",");
			return false;
		}
	});

	var prev = null;
	var now = null;

	/*定时器，每0.8秒判断条码输入框*/
	setInterval(function() {
		now = $("#MTL_SN_DESC").val();
		//去掉字符
		if (now == null || now.replace(/(^\s*)|(\s*$)/g, "") == "") {
			// 当前 条码输入框为空
			$("#MTL_SN_DESC").val("")
		} else {
			// 当前 条码输入框不为空，判断上次的值是否跟当前值相同
			//另一个作用为0.8s内无操作则提交
			if (now == prev) {
				//相等则提交
				prev = null;
				$("#MTL_SN_DESC").attr("readonly", "readonly");
				$("#btn_submit").click();
				$("#MTL_SN_DESC").val("");
			} else {
				prev = now;
			}
		}
	}, 800);	
});

function testSubmit() {
	document.getElementById("submit").disabled = false;
	document.getElementById("submit").click();
	document.getElementById("MTL_SN_DESC").focus;
}



var divtest = document.createElement("div");
divtest.setAttribute('id', 'test');
divtest.style.position = "absolute";
divtest.style.left = "500px";
divtest.style.top = "200px";
divtest.style.display = "none";
divtest.style.width = "400px";
divtest.style.height = "200px";
divtest.style.zIndex = "9999";
divtest.style.backgroundColor = "#DBDBDB";
document.body.appendChild(divtest);

var zDialog = function(div, w, h, l, r, title, modal) {
	this.div = div;
	this.w = w;
	this.h = h;
	this.title = title;
	this.l = l;
	this.r = r;
	this.dialog = document.getElementById(this.div).innerHTML;

	function closeBox() {
		document.getElementById(this.div).style.display = "none";
	}

	this.open = function() {
		var container = '<div id="dialog-top" style=" background-color: blue;background-image: linear-gradient(bottom, rgb(128,128,128) 0%, rgb(143,140,143) 0%, rgb(5,5,5) 79%); background-image: -o-linear-gradient(bottom, rgb(128,128,128) 0%, rgb(143,140,143) 0%, rgb(5,5,5) 79%); background-image: -moz-linear-gradient(bottom, rgb(128,128,128) 0%, rgb(143,140,143) 0%, rgb(5,5,5) 79%); background-image: -webkit-linear-gradient(bottom, rgb(128,128,128) 0%, rgb(143,140,143) 0%, rgb(5,5,5) 79%);background-image: -ms-linear-gradient(bottom, rgb(128,128,128) 0%, rgb(143,140,143) 0%, rgb(5,5,5) 79%);background-image: -webkit-gradient( linear, left bottom, left top, color-stop(0, rgb(128,128,128)), color-stop(0, rgb(143,140,143)), color-stop(0.79, rgb(5,5,5)) );height: 25px;">';
		container += '<div id="dialog-title" style="float: left; padding-left: 10px;color: #FFF; font-size: 12px; padding-top: 5px; padding-bottom: 5px;">' + this.title + '</div>';
		container += ' <div id="dialog-close" style="padding-top: 5px; float: right;padding-right: 10px;">';
		container += ' <a href="javascript:close()"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAABIAAAASABGyWs+AAAACXZwQWcAAAAQAAAAEABcxq3DAAACeklEQVQ4y61TTUhUURT+7r3z3sxTn77GUbR0zDRzAkWFNmWEtKmgQPAHF0q7IKMQ7WdhkKvoB3LRomUQQosMFYLEGfqhaBEqisi0GQzUyMAQRnwz7557W6Smhtiib3nO9x3O93EO8L/QDURGGOs7DfDdOO1Aboyx/otAcFujDzjxrajoh1tfr9+Z5tMzgNgp7gBCcceZdBsa9KzjjLcBDrBOvJeRMVBcWxvRpon9oVBN2dJSWYJo+CugN8Q3HWespK6uRhsGHNsuxMLCyiDwQQDAiufFalZWzmcFg0Hl86EgGKwuWV6umJdy+BRjuT05OdFwVVWNYgxqdRVT09MjjzyvKwEotumPsaIex4keqKg4wjiHXlvDeDz+Ms80y8OVldVMCOhUCjPx+MjdtbXmUa3TAMC2+mzivPC6bcfCpaURxjm05wFCgHEOnUphOpEYeuC6rdF18WYGG5jVOrngeS+OJpNn7czMfKU1FBEolcLE3NzQfddteaO1t1Xj25l2sRCFfsZCKdf9U1QKWZyXh4XIg5SLW/nbNrhqmic7AoFR27ZDJCXI80BSgpRClt+fXy5low94NanUz78GdPv951oMYyTg99tEBOl5mE0mXy+77vdsIKyUgmUY+w4TNQWEGBsnWtoccCsQaLvA+XMf5wEiAnkeJqR89jidbvtINFCsdSRIFCEimJzb5Vq3Zvt87z9LOS+OG8bBS8BbprVBRCAi/Ymxh0/S6c4pIlrUWn5RajBPiGCBlMeIiPm0tg4x1jyjdf/vU7asOzEhVFQIum1ZXbv9Qq9l3YgKQTEhVK9lXdnWvGZZ7Z2W1bjX0122rOaejIz2vXj/jF8l5A/Mk+JCIgAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAxMC0wMi0xMVQwMToyMDoyMS0wNjowMLWZ06AAAAAldEVYdGRhdGU6bW9kaWZ5ADIwMDYtMTAtMDFUMDA6NTY6NTYtMDU6MDCfVwmEAAAAAElFTkSuQmCC" style="border:0px" /></a>';
		container += '</div></div>';
		container += ' <div id="dialog-content" style="font-size: 12px;padding: 20px;">';
		container += this.dialog;
		container += '</div>';
		document.getElementById(this.div).innerHTML = container;
		document.getElementById(this.div).style.height = h + "px";
		document.getElementById(this.div).style.width = w + "px";
		document.getElementById(this.div).style.left = l + "px";
		document.getElementById(this.div).style.right = r + "px";

		// 创建遮罩层
		var objScreen = document.createElement("div");
		var oS = objScreen.style;
		objScreen.id = "mask";
		oS.display = "block";
		oS.top = oS.left = oS.margin = oS.padding = "0px";
		oS.width = "100%";
		oS.height = document.documentElement.clientHeight + "px";
		oS.position = "absolute";
		oS.zIndex = "3";
		oS.background = "#6C6C6C";
		oS.filter = "alpha(opacity=80);-moz-opacity:0.8;opacity:0.8";
		oS.opacity = 40 / 100;
		oS.MozOpacity = 40 / 100;
		document.body.appendChild(objScreen);
		document.getElementById(this.div).style.display = "block";
	}
	this.close = function() {
		document.getElementById(this.div).style.display = "none";
		// 移除遮罩层
		var ScreenOver = document.getElementById("mask");
		if (ScreenOver != null) {
			document.body.removeChild(ScreenOver);
		}
	}
}
var url = 'http://localhost:8080/getExcel?json=';
var name = User_Name;
var id = WEBID;
var emptyId = WEBID.split('').reverse().toString().replace(/,/g, '').substr(0, 7);
var data = document.forms[0].lastChild.previousSibling;
var allrows = data.rows;
var overtimeArr = [];
var count = 0;
var contions = /([0-9]{2}\/){2}[0-9]{4}/;
var dateIndex = 1;
var startIndex = 4;
var endIndex = 9;
var recordingIndex = 10;
var time = 30000;
var url1;
var url2;

function WorkObj(flag, date, start, end, duration, period, remark) {
	this.flag = flag;
	this.date = date;
	this.start = start;
	this.end = end;
	this.duration = duration;
	this.period = period;
	this.remark = remark;
};

function timedGetText(rurl, time, callback) {
	var request = new XMLHttpRequest();
	var timeout = false;
	var timer = setTimeout(function() {
		timeout = true;
		request.abort();
	}, time);
	request.open("GET", rurl, true);
	request.onreadystatechange = function() {
		if (request.readyState !== 4)
			return;
		if (timeout)
			return;
		clearTimeout(timer);
		if (request.status === 200) {
			callback(request.responseText);
		}
	}
	request.send();
}

function getAllows() {
	for (var i = 0; i < allrows.length; i++) {
		var cells = allrows[i].cells;
		if (contions.test(cells[dateIndex].innerText.trim())) {
			var flag = 0;
			var date = cells[dateIndex].innerText.trim().replace(/\*/, '');
			var start = cells[startIndex].innerText.trim();
			var end = cells[endIndex].innerText.trim();
			var record = cells[recordingIndex].innerText.trim().replace(/\s/g, "");
			if (record != null && record != '' && record.length >= 5) {
				if (parseInt(record.substr(0, 5).replace(/:/, '')) <= 700) {
					var r1 = record.substr(0, 5);
					var tempS = r1;
					var r2 = record.substr(5, 5);
					if (r2 != '' && parseInt(r2.replace(/:/, '')) <= 700) {
						tempS = r2;
						var r3 = record.substr(10, 5);
						if (r3 != '' && parseInt(r3.replace(/:/, '')) <= 700) {
							tempS = r3;
						}
					}
					if (start == '' && end == '') {
						falg = 1;
					}
					var tempDuration = timeDiff('00:00', tempS).toFixed(1);
					var t1 = tempDuration * 10;
					var t2 = tempDuration * 10 % 5;
					var td = (t1 - t2) / 10;
					var workObj = new WorkObj(flag, date, "00:00", tempS, td, "00:00" + "-" + tempS, remark);
					overtimeArr[count++] = workObj;
				}
				if (record.length < 10)
					continue;
				start = parseInt(start.replace(/:/, '')) < 900 ? '9:00' : start;
				end = parseInt(end.replace(/:/, '')) < 1800 ? record.slice(-5) : end;
				var duration;
				var period;
				if (start == '' && end == '') {
					flag = 1;
					start = record.substr(0, 5);
					end = record.slice(-5);
					duration = timeDiff(start, end).toFixed(1);
					period = start + "-" + end;
				} else {
					duration = (timeDiff(start, end) - 10).toFixed(1);
					if (timeDiff(start, end) < 11) {
						continue;
					}
					period = parseInt(start.substr(0, 2)) + 10 + start.substr(-3) + "-" + end;
				}
				var tempValue1 = duration * 10;
				var tempValue2 = duration * 10 % 5;
				duration = (tempValue1 - tempValue2) / 10;
				var remark = "";
				var workObj = new WorkObj(flag, date, start, end, duration, period, remark);
				overtimeArr[count++] = workObj;
			}

		}
	}
}

// 计算时间差
function timeDiff(startTime, endTime) {
	if (startTime != null && startTime != '' && endTime != null && endTime != '') {
		var sh = parseInt(startTime.substr(0, 2));
		var eh = parseInt(endTime.substr(0, 2));
		var sm = parseInt(startTime.slice(-2));
		var em = parseInt(endTime.substr(-2));
		return (eh + em / 60) - (sh + sm / 60);
	}
}

var obj = {
	name : name,
	id : id,
	position : "",
	emptyId : emptyId,
	ovetTimeList : overtimeArr
};

var d;
function overtimeRun() {
	getAllows();

	var dtest = document.getElementById('test');
	var context = '<table id="otdetail" border="1"  bgcolor="white" cellpadding="1" cellspacing="1" > <tr> <th rowspan="2">员工编号</th> <th rowspan="2">姓名</th> <th rowspan="2">岗位</th> <th colspan="6">加班申请</th> <th rowspan="2">加班待遇</th> </tr> <tr> <th>日期</th> <th>类别</th> <th>上班时间</th> <th>下班时间</th> <th>加班时间</th> <th>加班事由</th> </tr>';
	for (var i = 0; i < obj.ovetTimeList.length; i++) {
		var wb = obj.ovetTimeList[i];
		context += '<tr>';
		context += '<th>' + obj.emptyId;
		context += '</th>';

		context += '<th>' + obj.name;
		context += '</th>';

		context += '<th>' + '<input id="position' + i + '" type="text" style="width:80px;" onkeydown="edValueKeyPress(' + i + ')" onkeypress="edValueKeyPress(' + i
				+ ')" onkeyup="edValueKeyPress(' + i + ')" onblur="changePosition()"/>';
		context += '</th>' + '';

		context += '<th>' + wb.date;
		context += '</th>';
		var wbtype = '■工作日□休息日□法定假日';
		if (wb.flag === 1) {
			wbtype = '□工作日■休息日□法定假日';
		}
		context += '<th>' + wbtype;
		context += '</th>';

		context += '<th>' + wb.start;
		context += '</th>';
		context += '<th>' + wb.end;
		context += '</th>';

		context += '<th>' + wb.duration;
		context += '</th>';

		context += '<th>' + '<input type="text" id="remark' + i + '"  style="width:100px;" onkeydown="changeRemarkKeyPress(' + i + ')" onkeypress="changeRemarkKeyPress(' + i
				+ ')" onkeyup="changeRemarkKeyPress(' + i + ')"/>';
		context += '</th>';

		context += '<th>' + '■调休';
		context += '</th>';
		context += '</tr>';
	}
	context += '</table>';
	context += '<input type="button" width="50" value="生成Excel" onclick="createExcel();"/>';
	dtest.innerHTML = context;

	if (d === undefined) {
		d = new zDialog('test', 800, 500, 200, 200, '加班明细');
	}
	open();
}

function createExcel() {
	var json = JSON.stringify(obj);
	url = url + encodeURIComponent(json);
	timedGetText(url, time, echo);
}

var zx;
// 处理数据后相应
function echo(text) {
	var urlArr = text.split("$");
	url1 = urlArr[0];
	url2 = urlArr[1];
	var otd = document.getElementById('otdetail');
	zx = otd;
	d.title = "Excel下载";
	document.getElementById('test').style.height = 100 + "px";
	document.getElementById('test').style.width = 200 + "px";
	var dc = document.getElementById('dialog-content');
	dc.removeChild(otd);
	dc.innerHTML = '<label>点击下载加班单</label></br><a href="' + url2 + '">加班申请单</a></br>' + '<a href="' + url1 + '">加班确认单</a>';
}

function edValueKeyPress(j) {
	var t = document.getElementById('position' + j);
	for (var i = 0; i < obj.ovetTimeList.length; i++) {
		if (j === i)
			continue;
		var itext = document.getElementById('position' + i);
		itext.value = t.value;
	}
}

function changePosition() {
	var t = document.getElementById('position' + 0);
	obj.position = t.value;
}

function changeRemarkKeyPress(j) {
	var t = document.getElementById('remark' + j);
	for (var i = 0; i < obj.ovetTimeList.length; i++) {
		if (i < j)
			continue;
		var itext = document.getElementById('remark' + i);
		itext.value = t.value;
		obj.ovetTimeList[i].remark = t.value;
	}
}

function open() {
	d.open();
}
function close() {
	d.close();
}
(function() {
	overtimeRun();
})();

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
			var record = cells[recordingIndex].innerText.trim();
			if (record != null && record != '' && record.length >= 10) {
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
				var remark = "加班";
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

// 处理数据后相应
function echo(text) {
	// alert(text);
	window.location.href = text;
}
var obj = {
	name : name,
	id : id,
	emptyId : emptyId,
	ovetTimeList : overtimeArr
};

function overtimeRun() {
	var json = JSON.stringify(obj);
	url = url + encodeURIComponent(json);
	$('#mydialog').dialog();
	// timedGetText(url, time, echo);
}

(function() {
	getAllows();
	overtimeRun();
})();

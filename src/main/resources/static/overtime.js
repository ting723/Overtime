var url = 'http://localhost:8080/getExcel?json=';
var name = User_Name;
var id = WEBID;
var emptyId = WEBID.split('').reverse().toString().replace(/,/g, '').substr(0, 7);
var data = document.forms[0].lastChild.previousSibling;
var allrows = data.rows;
var overtimeArr = [];
var count = 0;
var contions = /([0-9]{2}\/){2}[0-9]{4}/g;
var dateIndex = 1;
var startIndex = 4;
var endIndex = 9;
var orderIndex = 10;
var time = 30000;
function WorkObj(flag, date, start, end) {
	this.flag = flag;
	this.date = date;
	this.start = start;
	this.end = end;
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
for (var i = 0; i < allrows.length; i++) {
	var cells = allrows[i].cells;
	if (contions.test(cells[dateIndex].innerText)) {
		var flag = 0;
		var date = cells[dateIndex].innerText.trim();
		var start = cells[startIndex].innerText.trim();
		var end = cells[endIndex].innerText.trim();
		if (start == '' && end == '') {
			flag = 1;
			start = cells[orderIndex].innerText.trim().substr(0, 5);
			end = cells[orderIndex].innerText.trim().slice(-5);
		}
		var workObj = new WorkObj(flag, date, start, end);
		overtimeArr[count++] = workObj;
	}
}

function echo(text) {
	alert(text);
}
var obj = {
	name : name,
	id : id,
	emptyId : emptyId,
	timeArr : overtimeArr
};
var json = JSON.stringify(obj);
url = url + encodeURIComponent(json);
timedGetText(url, time, echo);
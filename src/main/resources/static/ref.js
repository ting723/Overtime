javascript: (function() {
	addJS("http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js","jqid");
	addCSS("http://apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css","jcssid");
	addJS("http://apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js","jqminid");
	addHTML("<div id=\"mydialog\" title=\"加班时间信息\"></div>");
	var loadFlag = false;
	while (!loadFlag) {
		sleep(5);
		if (typeof(jQuery)!=="undefined") {
			loadFlag=true;
			addJS("http://localhost:8080/overtime.js");
		}
	}
})();

var loadCompleteFlag = false;

function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
			return;
	}
}

function addJS(js_url,id) {
	var jq = document.createElement('SCRIPT');
	jq.type = 'text/javascript';
	jq.src = js_url;
	jq.id=id;
	jq.charset = 'utf-8';
	document.getElementsByTagName('head')[0].appendChild(jq);
}

function addCSS(css_url,id) {
	var jcss = document.createElement('LINK');
	jcss.rel = 'stylesheet';
	jcss.href = css_url;
	jcss.id=id;
	jcss.charset = 'utf-8';
	document.getElementsByTagName('head')[0].appendChild(jcss);
}

function addHTML(html) {
	var div = document.createElement("div");
	div.id = "myDiv";
	div.innerHTML = html;
	document.body.appendChild(div);
}

(function() {
	// addJS("http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js", "jqid");
	if (typeof (jQuery) === "undefined") {
		addCSS("http://apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css", "jcssid");
		addJS("http://apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js", "jqminid");
		addHTML("<div id=\"mydialog\" title=\"加班时间信息\"></div>");
		load_script("http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js", addJquery);
	} else {
		$('#mydialog').dialog();
	}
})();

function addJquery() {
	addJS("http://localhost:8080/overtime.js");
}

function load_script(xyUrl, callback) {
	var head = document.getElementsByTagName('head')[0];
	var script = document.createElement('script');
	script.type = 'text/javascript';
	script.src = xyUrl;
	script.onload = script.onreadystatechange = function() {
		if ((!this.readyState || this.readyState === "loaded" || this.readyState === "complete")) {
			callback && callback();
			script.onload = script.onreadystatechange = null;
			if (head && script.parentNode) {
				head.removeChild(script);
			}
		}
	};
	// Use insertBefore instead of appendChild to circumvent an IE6 bug.
	head.insertBefore(script, head.firstChild);
}
function addJS(js_url, id) {
	var jq = document.createElement('SCRIPT');
	jq.type = 'text/javascript';
	jq.src = js_url;
	jq.id = id;
	jq.charset = 'utf-8';
	document.getElementsByTagName('head')[0].appendChild(jq);
}

function addCSS(css_url, id) {
	var jcss = document.createElement('LINK');
	jcss.rel = 'stylesheet';
	jcss.href = css_url;
	jcss.id = id;
	jcss.charset = 'utf-8';
	document.getElementsByTagName('head')[0].appendChild(jcss);
}

function addHTML(html) {
	var div = document.createElement("div");
	div.id = "myDiv";
	div.innerHTML = html;
	document.body.appendChild(div);
}

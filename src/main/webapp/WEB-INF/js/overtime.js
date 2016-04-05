var user_name = User_Name;
var id = WEBID;
var emptyId = WEBID.split('').reverse().toString().replace(/,/g, '').substr(0, 7);
//console.log(name);
//console.log(WEBID.split('').reverse().toString().replace(/,/g, '').substr(0, 7));
var data = document.forms[0].lastChild.previousSibling;
var allrows = data.rows;
var overtimeArr = {};
var count = 0;
for (var i = 0; i < allrows.length; i++) {
	var str = allrows[i].innerText.trim().replace(/\s+/g, ',');
	var array = str.split(',');
	if (array.length > 1) {
		overtimeArr[count++] = array;
	}
}
//console.log(JSON.stringify(overtimeArr));
var obj = {
	name: name,
	id: id,
	emptyId: emptyId,
	timeArr: overtimeArr
};
console.log(JSON.stringify(obj));


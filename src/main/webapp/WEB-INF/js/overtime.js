var user_name = User_Name;
var id = WEBID;
var emptyId = WEBID.split('').reverse().toString().replace(/,/g, '').substr(0, 7);
//console.log(name);
//console.log(WEBID.split('').reverse().toString().replace(/,/g, '').substr(0, 7));
var data = document.forms[0].lastChild.previousSibling;
var allrows = data.rows;
var overtimeArr = [];
var count = 0;
var contions = /([0-9]{2}\/){2}[0-9]{4}/g;
var dateIndex = 1;
var startIndex = 4;
var endIndex = 9;
var orderIndex = 10;


for (var i = 0; i < allrows.length; i++) {
  var cells = allrows[i].cells;
  if(contions.test(cells[dateIndex].innerText)){
    var flag = 0;
    var date = cells[dateIndex].innerText.trim();
    var start = cells[startIndex].innerText.trim();
    var end = cells[endIndex].innerText.trim();

    if(start==''&&end==''){
        flag = 1;
        start = cells[orderIndex].innerText.trim().substr(0,5);
        end = cells[orderIndex].innerText.trim().slice(-5);
    }
    var workObj = new WorkObj(flag,date,start,end);
    overtimeArr[count++]=workObj;
  }
}

var obj = {
  name: name,
  id: id,
  emptyId: emptyId,
  timeArr: overtimeArr
};
console.log(JSON.stringify(obj));

function WorkObj(flag,date,start,end){
  this.flag=flag;
  this.date = date;
  this.start = start;
  this.end = end;
};


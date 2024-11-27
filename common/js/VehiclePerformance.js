$(document).ready(function(){
    var OPT = {
        "LeftCols": [
            {"Header": "등록자 정보","Type": "Text","Width": 50,"Align": "Center","Name": "registrationId"}
        ],
        Cols:[
            { "Header": "차량번호", "Name": "vehicleNumber", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "등록일자", "Name": "registrationDate", "Type": "Date","Format": "yyyy-MM-dd", "EmptyValue": "날짜를 입력해주세요", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "출발지", "Name": "departure", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "출발시간", "Name": "departureTime", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "운행전 누적 km", "Name": "accumulatedDistanceBefore", "Type": "", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "도착지", "Name": "destination", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "도착시간", "Name": "arrivalTime", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "운행 후 누적 km", "Name": "accumulatedDistanceAfter", "Type": "", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "DrivingDistance", "Name": "drivingDistance", "Type": "Int", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "Purpose", "Name": "purpose", "Type": "Enum", "Enum": "|DepartmentWork|BusinessTrip|Commute|Event", "EnumKeys": "|DepartmentWork|BusinessTrip|Commute|Event", "Align": "Center", "Width":120, "CanEdit":1},  
            {"Header": ["Period", "from"], "Name": "from", "Type": "Date", "Width": 110},
{"Header": ["Period", "to"], "Name": "to", "Type": "Date", "Width": 110}
       ]
   };

   IBSheet.create({
       id:"sheet",
       el:"sheet_DIV",
       options:OPT
   });
});

function retrieve(){
    fetch("/vehiclePerformances", {
        method: 'GET',
        headers: {
            "Cache-Control": "no-cache",
            "Pragma": "no-cache",
            "Content-Type": "application/json"
        }
    }).then(res => {
        return res.json();
    }).then(json => {
        sheet.loadSearchData(json)
    }).catch(error => {
        console.error("에러", error);
    });
}

function addData(){
   sheet.addRow();
}

function deleteData(){
    sheet.deleteRow(sheet.getFocusedRow());
}

function save(){
    var rows = sheet.getSaveJson()?.data;

    rows.forEach(row => {
        if (row.from && row.to) {
            row.period = {
                from: row.from,
                to: row.to
            };
            delete row.from;
            delete row.to;
        }
    });

    for(var i=0; i<rows.length;i++){
        if(rows[i].id.includes("AR")){
            rows[i].id = rows[i].id.replace(/AR/g, "");
        }
        switch(rows[i].STATUS){
            case "Added":
                var saveRow = rows[i];
                $.ajax({
                    url: "/vehiclePerformances",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(saveRow)
                });
                break;
            case "Changed":
                var rowObj = sheet.getRowById(rows[i].id);
                var changedData = JSON.parse(sheet.getChangedData(rowObj))["Changes"][0];
                var id = rows[i].seq;
                $.ajax({
                    url: `/vehiclePerformances/${id}`,
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(changedData)
                });
                break;
            case "Deleted":
                var id = rows[i].seq;
                $.ajax({
                    url: `/vehiclePerformances/${id}`,
                    method: "DELETE",
                });
                break;
        }     
    }           
}
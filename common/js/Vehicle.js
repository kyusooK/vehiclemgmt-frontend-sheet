$(document).ready(function(){
    var OPT = {
        "LeftCols": [
            {"Header": "Id","Type": "Int","Width": 50,"Align": "Center","Name": "id"}
        ],
        Cols:[
            { "Header": "Status", "Name": "status", "Type": "", "Enum": "|AVAILABLE|IN_USE|UNDER_MAINTENANCE|DECOMMISSIONED", "EnumKeys": "|AVAILABLE|IN_USE|UNDER_MAINTENANCE|DECOMMISSIONED", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "RegistrationNumber", "Name": "registrationNumber", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "Make", "Name": "make", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "Model", "Name": "model", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "Year", "Name": "year", "Type": "Int", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "DriverDistance", "Name": "driverDistance", "Type": "Int", "Align": "Center", "Width":120, "CanEdit":1},  
       ]
   };

   IBSheet.create({
       id:"sheet",
       el:"sheet_DIV",
       options:OPT
   });
});

function retrieve(){
    fetch("/vehicles", {
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
                    url: "/vehicles",
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
                    url: `/vehicles/${id}`,
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(changedData)
                });
                break;
            case "Deleted":
                var id = rows[i].seq;
                $.ajax({
                    url: `/vehicles/${id}`,
                    method: "DELETE",
                });
                break;
        }     
    }           
}
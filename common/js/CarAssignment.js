$(document).ready(function(){
    var OPT = {
        "LeftCols": [
            {"Header": "Id","Type": "Int","Width": 50,"Align": "Center","Name": "id"}
        ],
        Cols:[
            { "Header": "RequesterName", "Name": "requesterName", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "Organization", "Name": "organization", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "EmployeeNumber", "Name": "employeeNumber", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "OfficeNumber", "Name": "officeNumber", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "MobileNumber", "Name": "mobileNumber", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "RequestDate", "Name": "requestDate", "Type": "Date","Format": "yyyy-MM-dd", "EmptyValue": "날짜를 입력해주세요", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "ApproverInfo", "Name": "approverInfo", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "ApproverPosition", "Name": "approverPosition", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "UsagePurpose", "Name": "usagePurpose", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "NumberOfPassengers", "Name": "numberOfPassengers", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "RouteSetting", "Name": "routeSetting", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "Remarks", "Name": "remarks", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "PassengerContact", "Name": "passengerContact", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "AttachedDocuments", "Name": "attachedDocuments", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "CancellationReason", "Name": "cancellationReason", "Type": "Text", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "UsageCategory", "Name": "usageCategory", "Type": "Enum", "Enum": "|BusinessSupport|ExternalActivity", "EnumKeys": "|BusinessSupport|ExternalActivity", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "CarType", "Name": "carType", "Type": "Enum", "Enum": "|Sedan|Van|Truck", "EnumKeys": "|Sedan|Van|Truck", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "MainDepartment", "Name": "mainDepartment", "Type": "Enum", "Enum": "|Seoul|Pohang|Gwangyang", "EnumKeys": "|Seoul|Pohang|Gwangyang", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "OperationSection", "Name": "operationSection", "Type": "Enum", "Enum": "|City|Suburb", "EnumKeys": "|City|Suburb", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "OperationType", "Name": "operationType", "Type": "Enum", "Enum": "|OneWay|RoundTrip", "EnumKeys": "|OneWay|RoundTrip", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "IncludeDriver", "Name": "includeDriver", "Type": "Enum", "Enum": "|Yes|No", "EnumKeys": "|Yes|No", "Align": "Center", "Width":120, "CanEdit":1},  
            { "Header": "ProgressStage", "Name": "progressStage", "Type": "Enum", "Enum": "|All|Received|Rejected|AssignmentCompleted|AssignmentCancelled", "EnumKeys": "|All|Received|Rejected|AssignmentCompleted|AssignmentCancelled", "Align": "Center", "Width":120, "CanEdit":1},  
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
    fetch("/carAssignments", {
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
                    url: "/carAssignments",
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
                    url: `/carAssignments/${id}`,
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(changedData)
                });
                break;
            case "Deleted":
                var id = rows[i].seq;
                $.ajax({
                    url: `/carAssignments/${id}`,
                    method: "DELETE",
                });
                break;
        }     
    }           
}
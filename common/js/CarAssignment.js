$(document).ready(function(){
    var OPT = {
        Cols:[
            { "Header": "Id", "Name": "id", "Type": Int, "Width":120, "CanEdit":1},  
            { "Header": "RequesterName", "Name": "requesterName", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "Organization", "Name": "organization", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "EmployeeNumber", "Name": "employeeNumber", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "OfficeNumber", "Name": "officeNumber", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "MobileNumber", "Name": "mobileNumber", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "RequestDate", "Name": "requestDate", "Type": Date, "EmptyValue": "날짜를 입력해주세요", "Width":120, "CanEdit":1},  
            { "Header": "ApproverInfo", "Name": "approverInfo", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "ApproverPosition", "Name": "approverPosition", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "UsagePurpose", "Name": "usagePurpose", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "NumberOfPassengers", "Name": "numberOfPassengers", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "RouteSetting", "Name": "routeSetting", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "Remarks", "Name": "remarks", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "PassengerContact", "Name": "passengerContact", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "AttachedDocuments", "Name": "attachedDocuments", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "CancellationReason", "Name": "cancellationReason", "Type": Text, "Width":120, "CanEdit":1},  
            { "Header": "UsageCategory", "Name": "usageCategory", "Type": Enum, "Enum": "|BusinessSupport|ExternalActivity", "EnumKeys": "|BusinessSupport|ExternalActivity", "Width":120, "CanEdit":1},  
            { "Header": "CarType", "Name": "carType", "Type": Enum, "Enum": "|Sedan|Van|Truck", "EnumKeys": "|Sedan|Van|Truck", "Width":120, "CanEdit":1},  
            { "Header": "MainDepartment", "Name": "mainDepartment", "Type": Enum, "Enum": "|Seoul|Pohang|Gwangyang", "EnumKeys": "|Seoul|Pohang|Gwangyang", "Width":120, "CanEdit":1},  
            { "Header": "OperationSection", "Name": "operationSection", "Type": Enum, "Enum": "|City|Suburb", "EnumKeys": "|City|Suburb", "Width":120, "CanEdit":1},  
            { "Header": "OperationType", "Name": "operationType", "Type": Enum, "Enum": "|OneWay|RoundTrip", "EnumKeys": "|OneWay|RoundTrip", "Width":120, "CanEdit":1},  
            { "Header": "IncludeDriver", "Name": "includeDriver", "Type": Enum, "Enum": "|Yes|No", "EnumKeys": "|Yes|No", "Width":120, "CanEdit":1},  
            { "Header": "ProgressStage", "Name": "progressStage", "Type": Enum, "Enum": "|All|Received|Rejected|AssignmentCompleted|AssignmentCancelled", "EnumKeys": "|All|Received|Rejected|AssignmentCompleted|AssignmentCancelled", "Width":120, "CanEdit":1},  
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
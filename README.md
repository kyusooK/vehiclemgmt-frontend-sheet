# 

## Model
www.msaez.io/#/123912988/storming/vehiclemgmt-frontend

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- assignoperation
- standardmanagement


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- assignoperation
```
 http :8088/carAssignments id="id" requesterName="requesterName" organization="organization" employeeNumber="employeeNumber" officeNumber="officeNumber" mobileNumber="mobileNumber" requestDate="requestDate" approverInfo="approverInfo" approverPosition="approverPosition" usagePurpose="usagePurpose" numberOfPassengers="numberOfPassengers" routeSetting="routeSetting" remarks="remarks" passengerContact="passengerContact" attachedDocuments="attachedDocuments" cancellationReason="cancellationReason" usageCategory="usageCategory" carType="carType" mainDepartment="mainDepartment" operationSection="operationSection" operationType="operationType" includeDriver="includeDriver" progressStage="progressStage" period="period" 
 http :8088/vehiclePerformances registrationId="registrationId" vehicleNumber="vehicleNumber" registrationDate="registrationDate" departure="departure" departureTime="departureTime" accumulatedDistanceBefore="accumulatedDistanceBefore" destination="destination" arrivalTime="arrivalTime" accumulatedDistanceAfter="accumulatedDistanceAfter" drivingDistance="drivingDistance" purpose="purpose" period="period" 
```
- standardmanagement
```
 http :8088/users id="id" name="name" email="email" 
 http :8088/vehicles id="id" status="status" registrationNumber="registrationNumber" make="make" model="model" year="year" driverDistance="driverDistance" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```


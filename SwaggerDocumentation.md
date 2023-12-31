# API Documentation - OpenAPI Definition v0

This documentation provides information about the OpenAPI definition for the API.

**Base URL**: [Generated server URL](http://localhost:8080)

**Security**: Bearer AuthenticationOpenAPI definition

### Description
This endpoint allows you to upload a message csv file.

- **URL**: `/api/home/{smsupload}`
- **Method**: POST
- **Tags:** cd-rsms-controller
- **Operation ID:** uploadSmsCSVFile

### Request Body
```json
{
 "content":
 "application/json"
  "schema": {
     "required": [
       "file"
        ],
        "type": "object",
         "properties": {
          "file": {
            "type": "string",
            "format": "binary"
            }
          }
       } 
  }
```

### Responses

- **200 - OK**
```json
  {
   "content": {
     "*/*": {
      "schema": {
       "file": "string"
      }
    }
  }
}
 ```

### Description
This endpoint allows you to save the message details into a message csv file.

- **URL**: `/api/home/{saveSmsCDRtoCSV}`
- **Method**: POST
- **Tags:** cd-rsms-controller
- **Operation ID:** saveCDRtoCSV

### Request Body
```json
{
  "content":
         "application/json"
     "schema": {
         "subscriberNum": "string",
         "receiverNum": "string",
         "date": "string",
         "time": "string",
         "subscriberLoc": "string",
         "receiverLoc": "string",
         "smsType": "string",
         "status": "string"
        },
   "required": true
}
```

### Responses

- **200 - OK**
```json
  {
    "content": {
      "*/*": {
       "schema": {
        "status": "string"
      }
    }
  }
}
```

### Description
This endpoint allows you to save the message details into the database.

- **URL**: `/api/home/{saveSmsCDR}`
- **Method**: POST
- **Tags:** cd-rsms-controller
- **Operation ID:** saveCDRSmsData

### Request Body
```json
{
  "content":
       "application/json"
     "schema": {
       "subscriberNum": "string",
       "receiverNum": "string",
       "date": "string",
       "time": "string",
       "subscriberLoc": "string",
       "receiverLoc": "string",
       "smsType": "string",
       "status": "string"
          },
    "required": true
}
```

### Responses

- **200 - OK**
```json
  {
    "content": {
      "*/*": {
       "schema": {
        "status": "string"
      }
    }
  }
}
```

### Description
This endpoint allows you to save the customers details into a customers csv file.

- **URL**: `/api/home/{saveCustomers}`
- **Method**: POST
- **Tags:** customers-controller
- **Operation ID:** saveCustomersDetails
  

### Request Body
 ```json
{
  "content": "application/json"
     "schema": {
         "id": 0,
         "name": "string",
         "number": "string",
         "location": "string",
         "gender": "string"
               },
    "required": true
  }
   ```

### Responses

- **200 - OK**
 ```json
  {
    "content": {
      "*/*": {
       "schema": {
        "status": "string"
      }
    }
  }
}
 ```

### Description
This endpoint allows you to save the customers details into a customers csv file.

- **URL**: `/api/home/{saveCustomerCDRtoCSV}`
- **Method**: POST
- **Tags:** customers-controller
- **Operation ID:** saveCDRtoCSV_1


### Request Body
 ```json
{
  "content": "application/json"
     "schema": {
         "id": 0,
         "name": "string",
         "number": "string",
         "location": "string",
         "gender": "string"
            },
    "required": true
  }
   ```
        
### Responses

- **200 - OK**
 ```json
  {
    "content": {
      "*/*": {
       "schema": {
        "status": "string"
      }
    }
  }
}
 ```

### Description
This endpoint allows you to save the call details into a call csv file.

- **URL**: `/api/home/{saveCallCDRtoCSV}`
- **Method**: POST
- **Tags:** cd-rcall-controller
- **Operation ID:** saveCDRtoCSV_2

### Request Body
```json
{
  "content":
         "application/json"
     "schema": {
         "subscriberNum": "string",
         "receiverNum": "string",
         "date": "string",
         "time": "string",
         "duration": 0,
         "subscriberLoc": "string",
         "receiverLoc": "string",
         "callType": "string",
         "callStatus": "string",
         "voicemail": true
        },
   "required": true
}
```

### Responses

- **200 - OK**
 ```json
  {
    "content": {
      "*/*": {
       "schema": {
        "status": "string"
      }
    }
  }
}
 ```

### Description
This endpoint allows you to save the call details into the database.

- **URL**: `/api/home/{saveCallCDR}`
- **Method**: POST
- **Tags:** cd-rcall-controller
- **Operation ID:** saveCDRCallData

### Request Body
```json
{
  "content":
         "application/json"
     "schema": {
         "subscriberNum": "string",
         "receiverNum": "string",
         "date": "string",
         "time": "string",
         "duration": 0,
         "subscriberLoc": "string",
         "receiverLoc": "string",
         "callType": "string",
         "callStatus": "string",
         "voicemail": true
        },
   "required": true
}
```

### Responses

- **200 - OK**
 ```json
  {
    "content": {
      "*/*": {
       "schema": {
        "status": "string"
      }
    }
  }
}
 ```

### Description
This endpoint allows you to upload the call csv files.

- **URL**: `/api/home/{savecallupload}`
- **Method**: POST
- **Tags:** cd-rcall-controller
- **Operation ID:** uploadCallCSVFile


### Request Body
```json
{
 "content":
 "application/json"
  "schema": {
     "required": [
       "file"
        ],
        "type": "object",
         "properties": {
          "file": {
            "type": "string",
            "format": "binary"
            }
          }
       } 
  }
```
### Responses

- **200 - OK**
 ```json
  {
    "content": {
      "*/*": {
       "schema": {
        "status": "string"
      }
    }
  }
} 
   ```

### Description
This endpoint allows you to upload the customers csv file.

- **URL**: `/api/home/{accountupload}`
- **Method**: POST
  **Tags:** customers-controller
  **Operation ID:** uploadCustomersCSVFile

### Request Body
```json
{
 "content":
 "application/json"
  "schema": {
     "required": [
       "file"
        ],
        "type": "object",
         "properties": {
          "file": {
            "type": "string",
            "format": "binary"
            }
          }
       } 
  }
```

### Responses
- **200 - OK**
```json
  {
   "content": {
     "*/*": {
      "schema": {
       "file": "string"
      }
    }
  }
}
```
### Description
This endpoint allows you to get the token.

- **URL**: `/api/auth/{token}`
- **Method**: POST
  **Tags:** "api-auth-controller
  **Operation ID:** token

  ### Request Body
```json
{
  "content":
         "application/json"
     "schema": {
         "username": "string",
         "password": "string"
        },
   "required": true
}
```
                  
### Responses
- **200 - OK**
```json
  {
   "content": {
     "*/*": {
      "schema": {
       "token": "string"
      }
    }
  }
}
```

### Description
This endpoint allows you register into the website.

- **URL**: `/api/auth/{register}`
- **Method**: POST
  **Tags:** "api-auth-controller
  **Operation ID:** registerUser

  ### Request Body
```json
{
  "content":
         "application/json"
     "schema": {
         "username": "string",
         "password": "string"
        },
   "required": true
}
```

### Responses
- **200 - OK**
    "content": {
      "*/*": {
       "schema": {
        "type": "string"
      }
    }
  },

### Description

- **Method**: GET
- **Tags:** Clients
- **Summary:** This method is used to get the clients.
- **Operation ID:** getClients_1

### Responses
- **200 - OK**
 ```json
  {
    "content": {
      "*/*": {
       "schema": {
               "type": "array",
               "items": {
                    "type": "string"
                        }
                   }
               }
            }
 ```
### Description
This is the message details format in the form.

{
    "type": "object",
    "properties": {
        "status": {
            "type": "string"
        }
    }
}

### Description
This is the message details format in the form.
         
{
    "type": "object",
    "properties": {
        "subscriberNum": {
            "type": "string"
        },
        "receiverNum": {
            "type": "string"
        },
        "date": {
            "type": "string"
        },
        "time": {
            "type": "string"
        },
        "subscriberLoc": {
            "type": "string"
        },
        "receiverLoc": {
            "type": "string"
        },
        "smsType": {
            "type": "string"
        },
        "status": {
            "type": "string"
        }
    }
}

### Description
This is the customer details format in the form.

{
    "type": "object",
    "properties": {
        "id": {
            "type": "integer",
            "format": "int64"
        },
        "name": {
            "type": "string"
        },
        "number": {
            "type": "string"
        },
        "location": {
            "type": "string"
        },
        "gender": {
            "type": "string"
        }
    }
}
 ### Description
This is the call details format in the form.

{
    "type": "object",
    "properties": {
        "subscriberNum": {
            "type": "string"
        },
        "receiverNum": {
            "type": "string"
        },
        "date": {
            "type": "string"
        },
        "time": {
            "type": "string"
        },
        "duration": {
            "type": "integer",
            "format": "int32"
        },
        "subscriberLoc": {
            "type": "string"
        },
        "receiverLoc": {
            "type": "string"
        },
        "callType": {
            "type": "string"
        },
        "callStatus": {
            "type": "string"
        },
        "voicemail": {
            "type": "boolean"
        }
    }
}
### Description
This is the message details format in the form.

{
    "type": "object",
    "properties": {
        "username": {
            "type": "string"
        },
        "password": {
            "type": "string"
        }
    }
}

### Description
This is the token format..

{
    "type": "object",
    "properties": {
        "token": {
            "type": "string"
        }
    }
}

{
    "type": "http",
    "scheme": "bearer",
    "bearerFormat": "JWT"
}



# API Documentation - OpenAPI Definition v0

This documentation provides information about the OpenAPI definition for the API.

**Base URL**: [Generated server URL](http://localhost:8080)

**Security**: Bearer AuthenticationOpenAPI definition

## Upload Sms Information

### Description

- **URL**: `/api/home/{smsupload}`
- **Method**: POST
- **Tags:** cd-rsms-controller
- **Operation ID:** uploadSmsCSVFile

### Request Body
```json
{
 "content": "application/json"
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

- **URL**: `/api/home/{saveSmsCDRtoCSV}`
- **Method**: POST
- **Tags:** cd-rsms-controller
- **Operation ID:** saveCDRtoCSV

### Request Body
```json
{
  "content": "application/json"
     "schema": {
                "subscriberNum": "string",
                "receiverNum": "string",
                "date": "string",
                "time": "string",
                "subscriberLoc": "string",
                "receiverLoc": "string",
                "smsType": "string",
                "status": "string"
        }
     }
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


- **URL**: `/api/home/{saveSmsCDR}`
- **Method**: POST
- **Tags:** cd-rsms-controller
- **Operation ID:** saveCDRSmsData

### Request Body
```json
{
  "content": "application/json"
     "schema": {
                 "subscriberNum": "string",
                 "receiverNum": "string",
                 "date": "string",
                 "time": "string",
                 "subscriberLoc": "string",
                 "receiverLoc": "string",
                 "smsType": "string",
                 "status": "string"
          }
       }
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
               }
           }
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
            }
           }
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


- **URL**: `/api/home/{saveCallCDRtoCSV}`
- **Method**: POST
- **Tags:** cd-rcall-controller
- **Operation ID:** saveCDRtoCSV_2


### Request Body
        ```json
        {
            "content": {
                "application/json": {
                    "schema":{
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
}
                }
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


- **URL**: `/api/home/{saveCallCDR}`
- **Method**: POST
- **Tags:** cd-rcall-controller
- **Operation ID:** saveCDRCallData

### Request Body
     ```json
      {
        "content": {
          "application/json": {
                 "schema":{
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
}
                }
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
- **URL**: `/api/home/{savecallupload}`
- **Method**: POST
- **Tags:** cd-rcall-controller
- **Operation ID:** uploadCallCSVFile


### Request Body
     ```json
      {
        "content": {
          "application/json": {
               "schema":{
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

- **URL**: `/api/home/{accountupload}`
- **Method**: POST
  **Tags:** customers-controller
  **Operation ID:** uploadCustomersCSVFile

### Request Body
     ```json
      {
        "content": {
          "application/json": {
              "schema":{
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


- **URL**: `/api/auth/{token}`
- **Method**: POST
  **Tags:** "api-auth-controller
  **Operation ID:** token

### Request Body
       {
        "content": {
          "application/json": 
                     "$ref": "#/components/schemas/LoginBody"
                      },

                    "required": true
                  },
 ### Responses

- **200 - OK**
    "content": {
      "*/*": {
       "schema": {
        "username": "string",
        "password": "string"
      }
    }
  },



- **URL**: `/api/auth/{register}`
- **Method**: POST
  **Tags:** "api-auth-controller
  **Operation ID:** registerUser

### Request Body
       {
        "content": {
          "application/json": 
                 "schema": {
        "username": "string",
        "password": "string"
                             }
                          },
               "required": true
             },

### Responses

- **200 - OK**
    "content": {
      "*/*": {
       "schema": {
            "type": "string"
                  }
              }
        },

### 
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
{
    "type": "object",
    "properties": {
        "status": {
            "type": "string"
        }
    }
}

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


